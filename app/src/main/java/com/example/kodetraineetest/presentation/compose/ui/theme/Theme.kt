package com.example.kodetraineetest.presentation.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Color(0xFF6534FF),
    primaryVariant = Color(0xFFF7F7F8),
    onPrimary = Color(0xFF050510),
    secondary = Color(0xFF55555C),
    secondaryVariant = Color(0xFF97979B),
    onSecondary = Color(0xFFC3C3C6),
    error = Color(0xFFF44336),
    onError = Color(0xFFFFFFFF),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF050510),
    surface = Color(0xFFE5E5E5),
    onSurface = Color(0xFFF3C4C4),
)

private val DarkColorPalette = darkColors(
    primary = Color(0xFF2900AC),
    primaryVariant = Color(0xFFF7F7F8),
    onPrimary = Color(0xFF9B9BC0),
    secondary = Color(0xFF4A4A50),
    secondaryVariant = Color(0xFF97979B),
    onSecondary = Color(0xFFA8A8AC),
    error = Color(0xFF68150F),
    onError = Color(0xFF9B9696),
    background = Color(0xFF838383),
    onBackground = Color(0xFF000000),
    surface = Color(0xFF2900AC),
    onSurface = Color(0xFF9B9BC0),
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography(),
        shapes = Shapes,
        content = content
    )
}