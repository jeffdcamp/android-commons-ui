package org.dbtools.android.commons.ui.compose.ext

import androidx.compose.material3.ListItemColors
import androidx.compose.ui.graphics.Color

/**
 * Simply ONLY change the ListItem containerColor to Color.Transparent
 *
 * Example:
 * ListItem(
 *     headlineContent = { "Hello" }
 *     colors = ListItemDefaults.colors().transparentContainer()
 * )
 */
fun ListItemColors.transparentContainer(): ListItemColors {
    return ListItemColors(
        containerColor = Color.Transparent,
        headlineColor = this.headlineColor,
        leadingIconColor = this.leadingIconColor,
        overlineColor = this.overlineColor,
        supportingTextColor = this.supportingTextColor,
        trailingIconColor = this.trailingIconColor,
        disabledHeadlineColor = this.disabledHeadlineColor,
        disabledLeadingIconColor = this.disabledLeadingIconColor,
        disabledTrailingIconColor = this.disabledTrailingIconColor,
    )
}
