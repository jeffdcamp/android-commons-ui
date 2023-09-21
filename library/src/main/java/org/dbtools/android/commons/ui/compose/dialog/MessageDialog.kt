package org.dbtools.android.commons.ui.compose.dialog

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp

@Composable
fun MessageDialog(
    onDismissRequest: () -> Unit,
    title: String? = null,
    text: String? = null,
    icon: @Composable (() -> Unit)? = null,
    confirmButtonText: @Composable () -> String? = { stringResource(android.R.string.ok) },
    onConfirmButtonClicked: (() -> Unit)? = null,
    dismissButtonText: @Composable () -> String? = { stringResource(android.R.string.cancel) },
    onDismissButtonClicked: (() -> Unit)? = null,
    tonalElevation: Dp = AlertDialogDefaults.TonalElevation
) {
    require(title != null || text != null) { "Title or Text is required (if visible)" }

    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = if (title != null) {
            { Text(title, style = MaterialTheme.typography.headlineSmall) }
        } else {
            null
        },
        text = if (text != null) {
            { Text(text, style = MaterialTheme.typography.bodyMedium) }
        } else {
            null
        },
        icon = icon,
        confirmButton = {
            val confirmButtonTextString = confirmButtonText()
            if (onConfirmButtonClicked != null && confirmButtonTextString != null) {
                TextButton(
                    onClick = {
                        onConfirmButtonClicked()
                    }
                ) {
                    Text(confirmButtonTextString)
                }
            }
        },
        dismissButton = {
            val dismissButtonTextString = dismissButtonText()
            if (onDismissButtonClicked != null && dismissButtonTextString != null) {
                TextButton(
                    onClick = {
                        onDismissButtonClicked()
                    }
                ) {
                    Text(dismissButtonTextString)
                }
            }
        },
        tonalElevation = tonalElevation
    )
}

@Composable
fun MessageDialog(
    dialogUiState: MessageDialogUiState
) {
    MessageDialog(
        onDismissRequest = dialogUiState.onDismissRequest,
        title = dialogUiState.title?.invoke(),
        text = dialogUiState.text?.invoke(),
        icon = dialogUiState.icon,
        confirmButtonText = dialogUiState.confirmButtonText,
        onConfirmButtonClicked = if (dialogUiState.onConfirm != null) { { dialogUiState.onConfirm.invoke(Unit) } } else null,
        dismissButtonText = dialogUiState.dismissButtonText,
        onDismissButtonClicked = dialogUiState.onDismiss,
    )
}

data class MessageDialogUiState(
    val title: @Composable (() -> String)? = null,
    val text: @Composable (() -> String)? = null,
    val icon: @Composable (() -> Unit)? = null,
    val confirmButtonText: @Composable () -> String? = { stringResource(android.R.string.ok) },
    val dismissButtonText: @Composable () -> String? = { stringResource(android.R.string.cancel) },
    override val onConfirm: ((Unit) -> Unit)? = {},
    override val onDismiss: (() -> Unit)? = null,
    override val onDismissRequest: () -> Unit = {},
) : DialogUiState<Unit>

@Preview(group = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
@Preview(group = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
@Composable
private fun PreviewMessageDialog() {
    MaterialTheme {
        MessageDialog(
            title = "Title",
            text = "This is the content that goes in the text",
            onDismissRequest = { },
            onConfirmButtonClicked = { },
            onDismissButtonClicked = { }
        )
    }
}

@Preview(group = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
@Preview(group = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
@Composable
private fun PreviewMessageDialogWithIcon() {
    MaterialTheme {
        MessageDialog(
            title = "Title",
            text = "This is the content that goes in the text",
            icon = { Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null) },
            onDismissRequest = { },
            onConfirmButtonClicked = { },
            onDismissButtonClicked = { }
        )
    }
}
