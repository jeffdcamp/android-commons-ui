package org.dbtools.android.commons.ui.compose.form

@Deprecated("Use FlowTextField")
data class TextFieldData(
    val text: String,
    val supportingText: String? = null,
    val isError: Boolean = false
)
