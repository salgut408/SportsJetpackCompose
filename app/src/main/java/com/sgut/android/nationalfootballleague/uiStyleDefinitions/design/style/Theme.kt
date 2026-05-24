package com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.material3.Typography as M3Typography

@Composable
fun Theme(
    isDark: Boolean = isSystemInDarkTheme(),
    colors: ColorScheme = if (isDark) DarkColorScheme else LightColorScheme,
    typography: Typography = Theme.typography,
    content: @Composable () -> Unit,
) {
    // Both custom schemes use dark backgrounds, so always base on darkColorScheme so
    // M3 components (Card, TopAppBar, TextField, etc.) inherit the right surface/text colors.
    val materialColorScheme = darkColorScheme(
        primary              = colors.primaryAccent,
        onPrimary            = colors.onPrimaryAccent,
        primaryContainer     = colors.primaryAccent.copy(alpha = 0.15f),
        onPrimaryContainer   = colors.onPrimaryAccent,
        secondary            = colors.secondaryAccent,
        onSecondary          = colors.onSecondaryAccent,
        secondaryContainer   = colors.secondaryAccent.copy(alpha = 0.15f),
        onSecondaryContainer = colors.onSecondaryAccent,
        background           = colors.background,
        onBackground         = colors.onBackground,
        surface              = colors.surface,
        onSurface            = colors.onSurface,
        surfaceVariant       = colors.surface,
        onSurfaceVariant     = colors.onSurface,
        error                = colors.error,
        onError              = colors.onError,
        errorContainer       = colors.error.copy(alpha = 0.2f),
        onErrorContainer     = colors.onError,
        outline              = colors.primaryAccent,
    )

    val materialTypography = M3Typography(
        titleLarge   = typography.titleL,
        titleMedium  = typography.titleM,
        titleSmall   = typography.titleS,
        headlineLarge = typography.titleL,
        headlineMedium = typography.titleM,
        headlineSmall = typography.titleS,
        bodyLarge    = typography.body,
        bodyMedium   = typography.body,
        bodySmall    = typography.small,
        labelLarge   = typography.subtitle,
        labelMedium  = typography.caption,
        labelSmall   = typography.caption,
    )

    val textSelectionColors = remember(colors) {
        TextSelectionColors(
            handleColor = colors.primaryAccent,
            backgroundColor = colors.primaryAccent.copy(alpha = 0.2f),
        )
    }

    MaterialTheme(colorScheme = materialColorScheme, typography = materialTypography) {
        CompositionLocalProvider(
            LocalColorScheme provides colors,
            LocalTextSelectionColors provides textSelectionColors,
            LocalTypography provides typography,
        ) {
            ProvideTextStyle(Theme.typography.body) {
                content()
            }
        }
    }
}

object Theme {

    val colors
        @Composable @ReadOnlyComposable get() = LocalColorScheme.current

    val typography
        @Composable @ReadOnlyComposable get() = LocalTypography.current
}