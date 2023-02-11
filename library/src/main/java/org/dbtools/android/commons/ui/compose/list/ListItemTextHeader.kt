package org.dbtools.android.commons.ui.compose.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun ListItemTextHeader(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    textPadding: PaddingValues = PaddingValues(start = 16.dp, top = 16.dp, bottom = 8.dp, end = 16.dp),
    dividerPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
) {
    Surface(modifier) {
        Column {
            Text(
                text = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(textPadding),
                color = color,
                style = style
            )
            Divider(modifier = Modifier.padding(dividerPadding))
        }
    }
}