package org.dbtools.android.commons.ui.compose.ext

import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Simply ONLY change the ListItem containerColor to Color.Transparent
 *
 * Example:
 * ListItem(
 *     headlineContent = { "Hello" }
 *     colors = ListItemDefaults.transparentContainerColors()
 * )
 */
@Composable
fun ListItemDefaults.transparentContainerColors(
    headlineColor: Color? = null,
    leadingIconColor: Color? = null,
    overlineColor: Color? = null,
    supportingColor: Color? = null,
    trailingIconColor: Color? = null,
    disabledHeadlineColor: Color? = null,
    disabledLeadingIconColor: Color? = null,
    disabledTrailingIconColor: Color? = null,
): ListItemColors {
    val defaultColors = colors()

    return colors(
        containerColor = Color.Transparent,
        headlineColor = headlineColor ?: defaultColors.headlineColor,
        leadingIconColor = leadingIconColor ?: defaultColors.leadingIconColor,
        overlineColor = overlineColor ?: defaultColors.overlineColor,
        supportingColor = supportingColor ?: defaultColors.supportingTextColor,
        trailingIconColor = trailingIconColor ?: defaultColors.trailingIconColor,
        disabledHeadlineColor = disabledHeadlineColor ?: defaultColors.disabledHeadlineColor,
        disabledLeadingIconColor = disabledLeadingIconColor ?: defaultColors.disabledLeadingIconColor,
        disabledTrailingIconColor = disabledTrailingIconColor ?: defaultColors.disabledTrailingIconColor,
    )
}
