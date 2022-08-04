package org.dbtools.android.commons.ui.compose.dialog

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun <T> RadioDialog(
    items: RadioDialogDataItems<T>?,
    onItemSelected: (T) -> Unit,
    title: String? = null,
    onConfirmButtonClicked: (() -> Unit)? = null,
    onDismissRequest: (() -> Unit) = {},
    onDismissButtonClicked: (() -> Unit)? = null,
    confirmButtonText: String = stringResource(android.R.string.ok),
    dismissButtonText: String = stringResource(android.R.string.cancel),
    shape: Shape = DialogDefaults.DefaultCorner,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    properties: DialogProperties = DialogProperties()
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Surface(
            shape = shape,
            color = backgroundColor
        ) {
            Column(
                modifier = Modifier.padding(DialogDefaults.DialogPadding)
            ) {
                // Title
                if (title != null) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                // Radio Items
                if (items != null) {
                    Box(Modifier.padding(top = 16.dp)) {
                        RadioDialogItems(items, onItemSelected)
                    }
                }

                // Buttons
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    if (onDismissButtonClicked != null) {
                        TextButton(
                            onClick = {
                                onDismissButtonClicked()
                            }
                        ) {
                            Text(dismissButtonText)
                        }
                    }
                    if (onConfirmButtonClicked != null) {
                        TextButton(
                            onClick = {
                                onConfirmButtonClicked()
                            }
                        ) {
                            Text(confirmButtonText)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun <T> RadioDialogItems(radioDialogDataItems: RadioDialogDataItems<T>, onItemSelected: (T) -> Unit) {
    Column(Modifier.selectableGroup()) {
        radioDialogDataItems.items.forEach { radioDialogDataItem ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .selectable(
                        selected = (radioDialogDataItem.item == radioDialogDataItems.selectedItem),
                        onClick = { onItemSelected(radioDialogDataItem.item) },
                        role = Role.RadioButton
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (radioDialogDataItem.item == radioDialogDataItems.selectedItem),
                    onClick = null // null recommended for accessibility with screen readers
                )
                Text(
                    text = radioDialogDataItem.text,
                    style = MaterialTheme.typography.bodyMedium.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun <T> RadioDialog(
    dialogUiState: RadioDialogUiState<T>
){
    RadioDialog(
        items = dialogUiState.items,
        onItemSelected = { dialogUiState.onConfirm(it) },
        title = dialogUiState.title,
        onConfirmButtonClicked = null,
        onDismissRequest = { dialogUiState.onDismissRequest() },
        onDismissButtonClicked = if (dialogUiState.onDismiss != null ) { { dialogUiState.onDismiss.invoke() } } else null,
        confirmButtonText = dialogUiState.confirmButtonText ?: stringResource(android.R.string.ok),
        dismissButtonText = dialogUiState.dismissButtonText ?: stringResource(android.R.string.cancel),
    )
}

data class RadioDialogUiState<T>(
    val items: RadioDialogDataItems<T>?,
    val title: String? = null,
    val confirmButtonText: String? = null,
    val dismissButtonText: String? = null,
    override val onConfirm: (T) -> Unit = {},
    override val onDismiss: (() -> Unit)? = null,
    override val onDismissRequest: () -> Unit = {},
) : DialogUiState<T>

data class RadioDialogDataItems<T>(val items: List<RadioDialogDataItem<T>>, val selectedItem: T)

data class RadioDialogDataItem<T>(val item: T, val text: String)

@Preview(group = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
@Preview(group = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
@Composable
private fun PreviewRadioDialog() {
    val radioItems: RadioDialogDataItems<String> = RadioDialogDataItems(
        listOf(
            RadioDialogDataItem("id1", "A"),
            RadioDialogDataItem("id2", "B"),
            RadioDialogDataItem("id3", "C"),
        ),
        "id2"
    )

    MaterialTheme {
        RadioDialog(
            title = "Title",
            items = radioItems,
            onItemSelected = {  },
            onDismissButtonClicked = {  }
        )
    }
}
