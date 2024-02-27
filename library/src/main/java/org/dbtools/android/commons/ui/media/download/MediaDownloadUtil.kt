@file:Suppress("unused")

package org.dbtools.android.commons.ui.media.download

import android.content.Context
import androidx.core.net.toUri
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.offline.Download
import androidx.media3.exoplayer.offline.DownloadManager
import androidx.media3.exoplayer.offline.DownloadRequest
import androidx.media3.exoplayer.offline.DownloadService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Download util to support ExoPlayer DownloadManager.
 *
 * This requires a DownloadManager and a MediaService class.
 * Examples for DownloadManager (if using Dagger/Hilt):
 *
 *     @Provides
 *     @Singleton
 *     fun provideMedia3DatabaseProvider(application: Application): DatabaseProvider {
 *         return StandaloneDatabaseProvider(application)
 *     }
 *
 *     @Provides
 *     @Singleton
 *     fun provideMedia3DownloadCache(application: Application, mediaDatabaseProvider: DatabaseProvider): Cache {
 *         // A download cache should not evict media, so should use a NoopCacheEvictor.
 *         val downloadDirectory = application.getExternalFilesDir(null) ?: application.filesDir
 *         downloadDirectory.mkdirs()
 *
 *         return SimpleCache(
 *             File(downloadDirectory, "downloads"),
 *             NoOpCacheEvictor(),
 *             mediaDatabaseProvider
 *         )
 *     }
 *
 *     @Provides
 *     @Singleton
 *     fun provideMedia3DownloadManager(application: Application, databaseProvider: DatabaseProvider, downloadCache: Cache): DownloadManager {
 *         // Create a factory for reading the data from the network.
 *         val dataSourceFactory = DefaultHttpDataSource.Factory()
 *
 *         // Choose an executor for downloading data. Using Runnable::run will cause each download task to
 *         // download data on its own thread. Passing an executor that uses multiple threads will speed up
 *         // download tasks that can be split into smaller parts for parallel execution. Applications that
 *         // already have an executor for background downloads may wish to reuse their existing executor.
 *         val downloadExecutor = Executor { obj: Runnable -> obj.run() }
 *
 *         // Create the download manager.
 *         val downloadManager = DownloadManager(
 *             application,
 *             databaseProvider,
 *             downloadCache,
 *             dataSourceFactory,
 *             downloadExecutor
 *         )
 *
 *         // Optionally, setters can be called to configure the download manager.
 *         downloadManager.maxParallelDownloads = 3
 *
 *         return downloadManager
 *     }
 *
 *
 *
 * @param context Android Context
 * @param downloadManager App provided ExoPlayer DownloadManager
 * @param mediaDownloadServiceClass App media DownloadService class (example: MediaDownloadService::class.java)
 */
@UnstableApi
class MediaDownloadUtil(
    private val context: Context,
    private val downloadManager: DownloadManager,
    private val mediaDownloadServiceClass: Class<out DownloadService?>
) {
    fun addDownload(id: String, url: String) {
        val downloadRequest: DownloadRequest = DownloadRequest.Builder(id, url.toUri()).build()
        DownloadService.sendAddDownload(
            context,
            mediaDownloadServiceClass,
            downloadRequest,
            false
        )
    }

    fun removeDownload(id: String) {
        DownloadService.sendRemoveDownload(
            context,
            mediaDownloadServiceClass,
            id,
            false
        )
    }

    fun stopDownload(id: String) {
        DownloadService.sendSetStopReason(
            context,
            mediaDownloadServiceClass,
            id,
            Download.STOP_REASON_NONE,
            false
        )
    }

    fun pauseDownloads() {
        DownloadService.sendPauseDownloads(
            context,
            mediaDownloadServiceClass,
            false
        )
    }

    fun resumeDownloads() {
        DownloadService.sendResumeDownloads(
            context,
            mediaDownloadServiceClass,
            false
        )
    }

    fun getDownload(id: String): Download? {

        return downloadManager.downloadIndex.getDownload(id)
    }

    private fun getDownloadProgress(id: String): MediaDownloadProgress? {
        val download = getDownload(id) ?: return null

        return MediaDownloadProgress(
            id = id,
            downloadInProgress = !download.isTerminalState,
            download.percentDownloaded / 100, // convert to 0.0 - 1.0 value (compatibility with Progress Indicator)
            download.state
        )
    }

    fun getDownloadProgressFlow(
        id: String,
        initialDelay: Long = DEFAULT_POLL_INITIAL_DELAY_MS,
        pollInterval: Long = DEFAULT_POLL_INTERVAL_MS
    ): Flow<MediaDownloadProgress> = flow {
        delay(initialDelay)

        var downloadProgress = getDownloadProgress(id) ?: return@flow
        emit(downloadProgress)

        while (downloadProgress.downloadInProgress.not()) {
            delay(pollInterval)

            downloadProgress = getDownloadProgress(id) ?: return@flow
            emit(downloadProgress)
        }
    }

    fun getDownloadProgressForeverFlow(
        id: String,
        initialDelay: Long = DEFAULT_POLL_INITIAL_DELAY_MS,
        pollInterval: Long = DEFAULT_FOREVER_POLL_INTERVAL_MS
    ): Flow<MediaDownloadProgress?> = flow {
        delay(initialDelay)

        var downloadProgress = getDownloadProgress(id)
        emit(downloadProgress)

        while (true) {
            delay(pollInterval)

            downloadProgress = getDownloadProgress(id)
            emit(downloadProgress)
        }
    }

    companion object {
        const val DEFAULT_POLL_INITIAL_DELAY_MS: Long = 0
        const val DEFAULT_POLL_INTERVAL_MS: Long = 100
        const val DEFAULT_FOREVER_POLL_INTERVAL_MS: Long = 1000
    }
}