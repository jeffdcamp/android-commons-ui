package org.dbtools.android.commons.ui.compose

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.StateFlow
import org.dbtools.android.commons.ui.compose.util.DateUiUtil
import java.time.LocalTime

@Preview(group = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
@Preview(group = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
@Composable
private fun TimeClickableTextField(label: String, localTimeFlow: StateFlow<LocalTime?>, onClick: () -> Unit) {
    val time by localTimeFlow.collectAsState()
    val text = DateUiUtil.getLocalTimeText(LocalContext.current, time)
    ClickableTextField(label, text, onClick)
}