package org.dbtools.android.commons.ui.compose.form

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.StateFlow
import org.dbtools.android.commons.ui.compose.util.DateUiUtil
import java.time.LocalDate

@Composable
fun DateClickableTextField(label: String, localDateFlow: StateFlow<LocalDate?>, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val date by localDateFlow.collectAsStateWithLifecycle()
    val text = DateUiUtil.getLocalDateText(LocalContext.current, date)
    ClickableTextField(label, text, onClick, modifier)
}
