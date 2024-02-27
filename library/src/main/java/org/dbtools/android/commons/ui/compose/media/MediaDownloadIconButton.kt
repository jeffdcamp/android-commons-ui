package org.dbtools.android.commons.ui.compose.media

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.StateFlow
import org.dbtools.android.commons.ui.media.download.MediaDownloadProgress

@Composable
fun MediaDownloadIconButton(
    mediaDownloadStateFlow: StateFlow<MediaDownloadProgress?>,
    downloadImageVector: ImageVector,
    downloadContentDescription: String?,
    downloadedImageVector: ImageVector,
    downloadedContentDescription: String?,
    onDownloadClicked: () -> Unit,
    onCancelDownloadClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val mediaDownloadState by mediaDownloadStateFlow.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
    ) {
        when (mediaDownloadState?.downloadInProgress) {
            true ->
                CircularProgressIndicator(
                    progress = { mediaDownloadState?.percentDownloaded ?: 0f },
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center)
                        .clickable {
                            onCancelDownloadClicked()
                        }
                )
            false -> IconButton(onClick = { onDownloadClicked() }) { Icon(downloadImageVector, downloadContentDescription) }
            else -> IconButton(onClick = { onDownloadClicked() }) { Icon(downloadedImageVector, downloadedContentDescription) }
        }
    }
}
