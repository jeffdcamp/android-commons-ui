package org.dbtools.android.commons.ui.compose.form

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import kotlinx.coroutines.flow.StateFlow
import org.dbtools.android.commons.ui.compose.DayNightTextField
import org.dbtools.android.commons.ui.compose.SupportingText
import org.dbtools.android.commons.ui.compose.util.formKeyEventHandler

@Composable
fun TextFieldDataTextField(label: String, textFlow: StateFlow<TextFieldData>, testTag: String, onChange: (String) -> Unit, modifier: Modifier = Modifier) {
    val textFieldValue by textFlow.collectAsState()
    val focusManager = LocalFocusManager.current

    DayNightTextField(
        value = textFieldValue.text,
        onValueChange = { onChange(it) },
        label = { Text(label) },
        singleLine = true,
        isError = textFieldValue.isError,
        supportingText = { SupportingText(textFieldValue.isError, textFieldValue.helperText, textFieldValue.errorHelperText) },
        modifier = modifier
            .onPreviewKeyEvent { formKeyEventHandler(it, focusManager) }
            .testTag(testTag)
    )
}
