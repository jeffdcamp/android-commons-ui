package org.dbtools.android.commons.ui.media.download

/**
 * MediaDownloadProgress for use with MediaDownloadUtil to show progress of downloading media from ExoPlayer DownloadManager
 *
 * @property id Unique id of the item being downloaded
 * @property downloadInProgress State of download. true if download is still in progress
 * @property percentDownloaded Progress of the current download
 * @property state ExoPlayer Download State (as defined in androidx.media3.exoplayer.offline.Download)
 */
data class MediaDownloadProgress(
    val id: String,
    val downloadInProgress: Boolean,
    val percentDownloaded: Float,
    val state: Int
)