package org.dbtools.android.commons.ui.compose.dialog

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun ProgressIndicatorDialog(
    onDismissRequest: () -> Unit = {},
    title: String? = null,
    dismissButtonText: String? = stringResource(android.R.string.cancel),
    onDismissButtonClicked: (() -> Unit)? = null,
    shape: Shape = MaterialTheme.shapes.medium,
    properties: DialogProperties = DialogProperties(),
    progressIndicatorColor: Color = MaterialTheme.colorScheme.secondary,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    textButtonColor: Color = MaterialTheme.colorScheme.primary,
    tonalElevation: Dp = AlertDialogDefaults.TonalElevation,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,

    ) {
        Surface(
            shape = shape,
            color = backgroundColor,
            tonalElevation = tonalElevation,
        ) {
            Column {
                // Title
                if (title != null) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    )
                }

                // Progress Indicator
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 32.dp),
                    color = progressIndicatorColor
                )

                // Button
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp)

                ) {
                    if (onDismissButtonClicked != null && dismissButtonText != null) {
                        TextButton(
                            onClick = {
                                onDismissButtonClicked()
                            }
                        ) {
                            Text(dismissButtonText, color = textButtonColor)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProgressIndicatorDialog(
    uiState: ProgressIndicatorDialogUiState
) {
    ProgressIndicatorDialog(
        title = uiState.title?.invoke(),
        dismissButtonText = uiState.dismissButtonText(),
        onDismissRequest = uiState.onDismissRequest,
        onDismissButtonClicked = uiState.onDismiss
    )
}

data class ProgressIndicatorDialogUiState(
    val title: @Composable (() -> String)? = null,
    val dismissButtonText: @Composable () -> String? = { stringResource(id = android.R.string.cancel) },
    override val onDismiss: (() -> Unit)? = {},
    override val onDismissRequest: () -> Unit = {}
) : DialogUiState<String> {
    // This is not used and shouldn't be implemented
    override val onConfirm: ((String) -> Unit)? = null
}

@Preview(group = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
@Preview(group = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
@Composable
private fun Preview() {
    MaterialTheme {
        ProgressIndicatorDialog(
            title = "Title",
            onDismissButtonClicked = { }
        )
    }
}
