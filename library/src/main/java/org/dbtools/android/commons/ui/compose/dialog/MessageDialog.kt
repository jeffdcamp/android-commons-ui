package org.dbtools.android.commons.ui.compose.dialog

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MessageDialog(
    onDismissRequest: () -> Unit,
    title: String? = null,
    text: String? = null,
    confirmButtonText: String = stringResource(android.R.string.ok),
    onConfirmButtonClicked: (() -> Unit)? = null,
    dismissButtonText: String = stringResource(android.R.string.cancel),
    onDismissButtonClicked: (() -> Unit)? = null
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
        confirmButton = {
            if (onConfirmButtonClicked != null) {
                TextButton(
                    onClick = {
                        onConfirmButtonClicked()
                    }
                ) {
                    Text(confirmButtonText)
                }
            }
        },
        dismissButton = {
            if (onDismissButtonClicked != null) {
                TextButton(
                    onClick = {
                        onDismissButtonClicked()
                    }
                ) {
                    Text(dismissButtonText)
                }
            }
        }
    )
}

@Composable
fun MessageDialog(
    dialogUiState: MessageDialogUiState
){
    MessageDialog(
        onDismissRequest = { dialogUiState.onDismissRequest() },
        title = dialogUiState.title,
        text = dialogUiState.text,
        confirmButtonText = dialogUiState.confirmButtonText ?: stringResource(android.R.string.ok),
        onConfirmButtonClicked = { dialogUiState.onConfirm(Unit) },
        dismissButtonText = dialogUiState.dismissButtonText ?: stringResource(android.R.string.cancel),
        onDismissButtonClicked = if (dialogUiState.onDismiss != null) { { dialogUiState.onDismiss.invoke() } } else null
    )
}

data class MessageDialogUiState(
    val title: String? = null,
    val text: String? = null,
    val confirmButtonText: String? = null,
    val dismissButtonText: String? = null,
    override val onConfirm: (Unit) -> Unit = {},
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
