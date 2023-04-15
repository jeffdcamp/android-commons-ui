package org.dbtools.android.commons.ui.compose.ext

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp

/**
 * Int sp that is used to prevent scaling text with changes to OS Font Size
 * NOTE: This may change nominally in size based on the calculations within the function below.  Use 'with(LocalDensity.current) { 42.toSp() }' if pixel-perfect is required
 */
val Int.nonScaledSp
    @Composable
    get() = (this / LocalDensity.current.fontScale).sp

/**
 * Double sp that is used to prevent scaling text with changes to OS Font Size
 * NOTE: This may change nominally in size based on the calculations within the function below.  Use 'with(LocalDensity.current) { 42.toSp() }' if pixel-perfect is required
 */
val Double.nonScaledSp
    @Composable
    get() = (this / LocalDensity.current.fontScale).sp

/**
 * Float sp that is used to prevent scaling text with changes to OS Font Size
 * NOTE: This may change nominally in size based on the calculations within the function below.  Use 'with(LocalDensity.current) { 42.toSp() }' if pixel-perfect is required
 */
val Float.nonScaledSp
    @Composable
    get() = (this / LocalDensity.current.fontScale).sp
