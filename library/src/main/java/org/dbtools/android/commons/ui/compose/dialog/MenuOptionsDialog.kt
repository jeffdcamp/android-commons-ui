package org.dbtools.android.commons.ui.compose.dialog

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun MenuOptionsDialog(
    onDismissRequest: (() -> Unit),
    title: String? = null,
    options: List<MenuOptionsDialogItem>,
    properties: DialogProperties = DialogProperties(),
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            color = backgroundColor,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column {
                // Title
                if (title != null) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Options
                options.forEach { menuOptionsDialogItem: MenuOptionsDialogItem ->
                    ListItem(
                        headlineText = { Text(menuOptionsDialogItem.text()) },
                        modifier = Modifier
                            .clickable { menuOptionsDialogItem.onClick() }
                    )
                }
            }
        }
    }
}

data class MenuOptionsDialogItem(val text: @Composable () -> String, val onClick: () -> Unit)

@Composable
fun MenuOptionsDialog(
    dialogUiState: MenuOptionsDialogUiState
) {
    MenuOptionsDialog(
        onDismissRequest = dialogUiState.onDismissRequest,
        title = dialogUiState.title(),
        options = dialogUiState.options
    )
}

data class MenuOptionsDialogUiState(
    val title: @Composable () -> String? = { null },
    val options: List<MenuOptionsDialogItem>,
    override val onConfirm: ((String) -> Unit)? = null, // not used in OptionsDialog
    override val onDismiss: (() -> Unit)? = null,  // not used in OptionsDialog
    override val onDismissRequest: () -> Unit = {}
) : DialogUiState<String>

@Preview(group = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
@Preview(group = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
@Composable
fun PreviewMenuOptionsDialog() {
    MaterialTheme {
        MenuOptionsDialog(
            onDismissRequest = {},
            title = "Options",
            options = listOf(
                MenuOptionsDialogItem({ "Option 1" }) {},
                MenuOptionsDialogItem({ "Option 2" }) {},
                MenuOptionsDialogItem({ "Option 3" }) {},
            )
        )
    }
}