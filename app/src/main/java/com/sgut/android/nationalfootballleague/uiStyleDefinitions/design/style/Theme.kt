package com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

@Composable
fun Theme(
    isDark: Boolean = isSystemInDarkTheme(),
    colors: ColorScheme = if (isDark) DarkColorScheme else LightColorScheme,
    typography: Typography = Theme.typography,
    content: @Composable () -> Unit,
) {

//    val rippleIndication = ripple()
    val textSelectionColors = remember(colors) { TextSelectionColors(
        handleColor = colors.primaryAccent,
        backgroundColor = colors.primaryAccent.copy(alpha = 0.2F),
    ) }

    CompositionLocalProvider(
        LocalColorScheme provides colors,
//        LocalIndication provides rippleIndication,
        LocalTextSelectionColors provides textSelectionColors,
        LocalTypography provides typography,
    ) {
        ProvideTextStyle(Theme.typography.body) {
            content()
        }
    }
}

object Theme {

    val colors
        @Composable @ReadOnlyComposable get() = LocalColorScheme.current

    val typography
        @Composable @ReadOnlyComposable get() = LocalTypography.current

}
