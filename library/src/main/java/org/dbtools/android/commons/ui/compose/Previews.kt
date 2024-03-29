package org.dbtools.android.commons.ui.compose

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@PreviewLibraryLightDarkDevices
internal annotation class PreviewLibraryDefault

@PreviewLibraryLightDarkDevices
@PreviewLibraryMultiSizeDevices
internal annotation class PreviewLibraryAll

@Preview(group = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
@Preview(group = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
internal annotation class PreviewLibraryLightDarkDevices

@Preview(group = "Phone", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480", name = "Phone Landscape", showBackground = true)
@Preview(group = "Phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480", name = "Phone Portrait", showBackground = true)
@Preview(group = "Tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480", name = "Tablet Landscape", showBackground = true)
@Preview(group = "Tablet", device = "spec:shape=Normal,width=800,height=1280,unit=dp,dpi=480", name = "Tablet Portrait", showBackground = true)
internal annotation class PreviewLibraryMultiSizeDevices