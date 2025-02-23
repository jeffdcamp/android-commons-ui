@file:Suppress("unused")

package org.dbtools.android.commons.ui.compose.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ImageScrim(
    modifier: Modifier = Modifier,
    alpha: Float = 0.15f,
    color: Color = Color.Black
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color.copy(alpha = alpha))
    )
}

@Composable
fun BoxScope.ImageScrimTopDown(
    modifier: Modifier = Modifier,
    alpha: Float = 0.15f,
    height: Dp = 100.dp,
    color: Color = Color.Black
) {
    Box(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .align(Alignment.TopCenter)
            .background(brush = Brush.verticalGradient(colors = listOf(color.copy(alpha = alpha), Color.Transparent)))
    )
}

@Composable
fun BoxScope.ImageScrimBottomUp(
    modifier: Modifier = Modifier,
    alpha: Float = 0.15f,
    height: Dp = 100.dp,
    color: Color = Color.Black
) {
    Box(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .background(brush = Brush.verticalGradient(colors = listOf(Color.Transparent, color.copy(alpha = alpha))))
    )
}

@Composable
fun BoxScope.ImageScrimStartEnd(
    modifier: Modifier = Modifier,
    alpha: Float = 0.15f,
    width: Dp = 100.dp,
    color: Color = Color.Black
) {
    Box(
        modifier = modifier
            .width(width)
            .fillMaxHeight()
            .align(Alignment.CenterStart)
            .background(brush = Brush.horizontalGradient(colors = listOf(color.copy(alpha = alpha), Color.Transparent)))
    )
}

@Composable
fun BoxScope.ImageScrimEndStart(
    modifier: Modifier = Modifier,
    alpha: Float = 0.15f,
    width: Dp = 100.dp,
    color: Color = Color.Black
) {
    Box(
        modifier = modifier
            .width(width)
            .fillMaxHeight()
            .align(Alignment.CenterEnd)
            .background(brush = Brush.horizontalGradient(colors = listOf(Color.Transparent, color.copy(alpha = alpha))))
    )
}
