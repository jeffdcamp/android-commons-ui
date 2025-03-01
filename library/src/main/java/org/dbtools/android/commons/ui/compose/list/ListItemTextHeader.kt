package org.dbtools.android.commons.ui.compose.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.dbtools.android.commons.ui.compose.ext.transparentContainerColors

@Composable
fun ListItemTextHeader(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    style: TextStyle = MaterialTheme.typography.titleSmall,
    textPadding: PaddingValues = PaddingValues(start = 16.dp, top = 16.dp),
) {
    Surface(
        color = backgroundColor,
        modifier = modifier
    ) {
        Column {
            Text(
                text = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(textPadding),
                color = color,
                style = style
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MaterialTheme {
        Surface(
//            color = Color.LightGray,
        ) {
            ElevatedCard {

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    ListItemTextHeader(
                        text = "Food",
                        backgroundColor = Color.Transparent
                    )
                    ListItem(
                        headlineContent = { Text("Pizza") },
                        colors = ListItemDefaults.transparentContainerColors(),
                    )
                    ListItem(headlineContent = { Text("Taco") })
                    ListItem(headlineContent = { Text("Hot Dog") })
                    ListItem(headlineContent = { Text("Banana") })

                    ListItemTextHeader(text = "Colors")
                    ListItem(headlineContent = { Text("Red") })
                    ListItem(headlineContent = { Text("Green") })
                    ListItem(headlineContent = { Text("Blue") })
                    ListItem(headlineContent = { Text("Yellow") })
                }
            }
        }
    }
}
