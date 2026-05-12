package com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.tokens.ColorSchemeKeyTokens


@Immutable
class ColorScheme(
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,

    val primaryAccent: Color,
    val onPrimaryAccent: Color,
    val secondaryAccent: Color,
    val onSecondaryAccent: Color,

    val success: Color,
    val onSuccess: Color,
    val warn: Color,
    val onWarn: Color,
    val error: Color,
    val onError: Color,
)

@Stable
fun ColorScheme.contentColorFor(color: Color): Color = when (color) {
    background -> onBackground
    surface -> onSurface

    primaryAccent -> onPrimaryAccent
    secondaryAccent -> onSecondaryAccent

    success -> onSuccess
    warn -> onWarn
    error -> onError

    else -> Color.Unspecified
}

@Composable
@ReadOnlyComposable
fun contentColorFor(backgroundColor: Color) =
    Theme.colors.contentColorFor(backgroundColor).takeOrElse {
        LocalContentColor.current
    }

@Stable
internal fun ColorScheme.fromToken(value: ColorSchemeKeyTokens): Color {
    return when (value) {
        ColorSchemeKeyTokens.Background -> background
        ColorSchemeKeyTokens.OnBackground -> onBackground
        ColorSchemeKeyTokens.Surface -> surface
        ColorSchemeKeyTokens.OnSurface -> onSurface

        ColorSchemeKeyTokens.PrimaryAccent -> primaryAccent
        ColorSchemeKeyTokens.OnPrimaryAccent -> onPrimaryAccent
        ColorSchemeKeyTokens.SecondaryAccent -> secondaryAccent
        ColorSchemeKeyTokens.OnSecondaryAccent -> onSecondaryAccent

        ColorSchemeKeyTokens.Success -> success
        ColorSchemeKeyTokens.OnSuccess -> onSuccess
        ColorSchemeKeyTokens.Warn -> warn
        ColorSchemeKeyTokens.OnWarn -> onWarn
        ColorSchemeKeyTokens.Error -> error
        ColorSchemeKeyTokens.OnError -> onError
    }
}
// NEON SCHEME
val LightColorScheme = ColorScheme(
    background = Color(0xFF0F0F1A),        // Deep navy base for contrast
    onBackground = Color(0xFFE0E0FF),      // Soft neon blue-white

    surface = Color(0xFF212139),           // Slightly raised dark surface
    onSurface = Color(0xFFCCF0FF),         // Soft cyan glow text

    primaryAccent = Color(0xFF00FFE0),     // Neon cyan
    onPrimaryAccent = Color(0xFF000000),   // Black for high contrast

    secondaryAccent = Color(0xFFFF00F7),   // Neon magenta
    onSecondaryAccent = Color(0xFF000000),

    success = Color(0xFF00FF9F),           // Neon green
    onSuccess = Color(0xFF001F12),         // Deep greenish-black

    warn = Color(0xFFFFA500),              // Bright neon orange
    onWarn = Color(0xFF2A1A00),

    error = Color(0xFFFF0055),             // Neon pinkish red
    onError = Color(0xFF2B0000),
)

val DarkColorScheme = ColorScheme(
    background = Color(0xFF000000),        // Pure black for max neon glow
    onBackground = Color(0xFF00FFE0),      // Bright cyan text

    surface = Color(0xFF101010),           // Dark gray surface
    onSurface = Color(0xFFAAFFEE),         // Dimmed neon for contrast

    primaryAccent = Color(0xFFFF00F7),     // Neon magenta
    onPrimaryAccent = Color(0xFF000000),

    secondaryAccent = Color(0xFF00FF9F),   // Neon green
    onSecondaryAccent = Color(0xFF000000),

    success = Color(0xFF00FF66),           // Stronger neon green
    onSuccess = Color(0xFF002B1A),

    warn = Color(0xFFFFD300),              // Neon yellow-orange
    onWarn = Color(0xFF332400),

    error = Color(0xFFFF3366),             // Bright neon red
    onError = Color(0xFF2A0005),
)

val LocalColorScheme = staticCompositionLocalOf { LightColorScheme }

internal val ColorSchemeKeyTokens.value: Color
    @ReadOnlyComposable @Composable get() = Theme.colors.fromToken(this)

@Composable
fun ProvideContentColor(color: Color, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalContentColor provides color, content = content)
}
