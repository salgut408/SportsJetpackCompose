package com.sgut.android.nationalfootballleague.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver


const val MinContrastOfPrimaryVsSurface = 3f


@Composable
fun ColorScheme.compositedOnSurface(alpha: Float): Color {
    return onSurface.copy(alpha = alpha).compositeOver(surface)
}

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val md_theme_light_primary = Color(0xFF00FFF7)             // Neon cyan
val md_theme_light_onPrimary = Color(0xFF000000)
val md_theme_light_primaryContainer = Color(0xFFCCFFF9)    // Pale neon container
val md_theme_light_onPrimaryContainer = Color(0xFF00332F)

val md_theme_light_secondary = Color(0xFFFF00F7)           // Neon pink
val md_theme_light_onSecondary = Color(0xFF000000)
val md_theme_light_secondaryContainer = Color(0xFFFFCCFA)
val md_theme_light_onSecondaryContainer = Color(0xFF330032)

val md_theme_light_tertiary = Color(0xFFFFFF00)            // Neon yellow
val md_theme_light_onTertiary = Color(0xFF000000)
val md_theme_light_tertiaryContainer = Color(0xFFFFFFCC)
val md_theme_light_onTertiaryContainer = Color(0xFF333300)

val md_theme_light_error = Color(0xFFFF3C38)               // Bright neon red
val md_theme_light_errorContainer = Color(0xFFFFC7C6)
val md_theme_light_onError = Color(0xFF330000)
val md_theme_light_onErrorContainer = Color(0xFF5A0000)

val md_theme_light_background = Color(0xFF0B0F1E)          // Dark canvas with neon elements
val md_theme_light_onBackground = Color(0xFFE1FFE9)

val md_theme_light_surface = Color(0xFF10152A)
val md_theme_light_onSurface = Color(0xFFCCF9FF)

val md_theme_light_surfaceVariant = Color(0xFF0D1C2F)
val md_theme_light_onSurfaceVariant = Color(0xFF66E0FF)

val md_theme_light_outline = Color(0xFF00FFFF)
val md_theme_light_inverseOnSurface = Color(0xFF10152A)
val md_theme_light_inverseSurface = Color(0xFFE1FFE9)
val md_theme_light_inversePrimary = Color(0xFF00FFFF)
val md_theme_light_shadow = Color(0xFF000000)
val md_theme_light_surfaceTint = Color(0xFF00FFF7)
val md_theme_light_outlineVariant = Color(0xFF00FFC3)
val md_theme_light_scrim = Color(0xFF00FFC3)

val md_theme_dark_primary = Color(0xFF00FFF7)
val md_theme_dark_onPrimary = Color(0xFF003332)
val md_theme_dark_primaryContainer = Color(0xFF003B3A)
val md_theme_dark_onPrimaryContainer = Color(0xFFCCFFF9)

val md_theme_dark_secondary = Color(0xFFFF00F7)
val md_theme_dark_onSecondary = Color(0xFF3A0039)
val md_theme_dark_secondaryContainer = Color(0xFF4A004A)
val md_theme_dark_onSecondaryContainer = Color(0xFFFFCCFA)

val md_theme_dark_tertiary = Color(0xFFFFFF00)
val md_theme_dark_onTertiary = Color(0xFF3F3F00)
val md_theme_dark_tertiaryContainer = Color(0xFF5C5C00)
val md_theme_dark_onTertiaryContainer = Color(0xFFFFFFCC)

val md_theme_dark_error = Color(0xFFFF3C38)
val md_theme_dark_errorContainer = Color(0xFF5A0000)
val md_theme_dark_onError = Color(0xFFFFDAD6)
val md_theme_dark_onErrorContainer = Color(0xFFFFC7C6)

val md_theme_dark_background = Color(0xFF0B0F1E)
val md_theme_dark_onBackground = Color(0xFFE1FFE9)

val md_theme_dark_surface = Color(0xFF10152A)
val md_theme_dark_onSurface = Color(0xFFB5FFFF)

val md_theme_dark_surfaceVariant = Color(0xFF0D1C2F)
val md_theme_dark_onSurfaceVariant = Color(0xFF66E0FF)

val md_theme_dark_outline = Color(0xFF00FFFF)
val md_theme_dark_inverseOnSurface = Color(0xFF10152A)
val md_theme_dark_inverseSurface = Color(0xFFE1FFE9)
val md_theme_dark_inversePrimary = Color(0xFF00FFF7)
val md_theme_dark_shadow = Color(0xFF000000)