package org.dbtools.android.commons.ui.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.StateFlow
import org.dbtools.android.commons.ui.compose.util.formKeyEventHandler

@Composable
fun TextFieldDataTextField(label: String, textFlow: StateFlow<TextFieldData>, testTag: String, onChange: (String) -> Unit) {
    val textFieldValue by textFlow.collectAsState()
    val focusManager = LocalFocusManager.current

    DayNightTextField(
        value = textFieldValue.text,
        onValueChange = { onChange(it) },
        label = { Text(label) },
        singleLine = true,
        isError = textFieldValue.isError,
        supportingText = { SupportingText(textFieldValue.isError, textFieldValue.helperText, textFieldValue.errorHelperText) },
        modifier = Modifier
            .onPreviewKeyEvent { formKeyEventHandler(it, focusManager) }
            .fillMaxWidth()
            .padding(top = 16.dp)
            .testTag(testTag)
    )
}