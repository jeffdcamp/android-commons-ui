@file:Suppress("unused")

package org.dbtools.android.commons.ui.compose.form

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.StateFlow
import org.dbtools.android.commons.ui.compose.DayNightTextField

@Composable
fun <T> DropdownMenuBoxField(
    options: List<T>,
    selectedOptionFlow: StateFlow<T>,
    onOptionSelected: (T) -> Unit,
    optionToText: @Composable (T) -> String,
    modifier: Modifier = Modifier,
    label: String? = null
) {
    val selectedOption by selectedOptionFlow.collectAsStateWithLifecycle()

    DropdownMenuBoxField(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = onOptionSelected,
        optionToText = optionToText,
        modifier = modifier,
        label = label
    )
}

@Composable
fun <T> DropdownMenuBoxField(
    options: List<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit,
    optionToText: @Composable (T) -> String,
    modifier: Modifier = Modifier,
    label: String? = null
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        DayNightTextField(
            readOnly = true,
            value = optionToText(selectedOption),
            onValueChange = {},
            label = if (label != null) { { Text(text = label) } } else null,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = modifier
                // As of Material3 1.0.0-beta03; The `menuAnchor` modifier must be passed to the text field for correctness.
                // (https://android-review.googlesource.com/c/platform/frameworks/support/+/2200861)
                .menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(optionToText(option)) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}
