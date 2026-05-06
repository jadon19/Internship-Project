package com.example.kasthakala.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = BrownPrimary,
    secondary = BrownSecondary,
    tertiary = WoodAccent
)

@Composable
fun KashtaKalaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        content = content
    )
}