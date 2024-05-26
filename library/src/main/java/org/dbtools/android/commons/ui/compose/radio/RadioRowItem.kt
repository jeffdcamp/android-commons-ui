package org.dbtools.android.commons.ui.compose.radio

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonTextItem(text: String, selected: Boolean?, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier
            .height(40.dp)
            .selectable(
                selected = selected == true,
                onClick = onClick,
                role = Role.RadioButton
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected == true,
            onClick = null
        )
        Text(
            text = text,
//            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MaterialTheme {
        Surface {
            Column {
                RadioButtonTextItem("Yes", true, {})
                RadioButtonTextItem("No", false, {})
            }
        }
    }

}