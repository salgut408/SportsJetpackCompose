package com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style

import androidx.compose.runtime.compositionLocalOf

@Suppress("ConstPropertyName")
object ContentAlpha {
    const val primary = 1.00F
    const val secondary = 0.76F
    const val tertiary = 0.54F

    const val disabled = 0.36F

    const val selection = 0.18F
    const val border = 0.12F
    const val highlight = 0.06F
}


/**
 * [androidx.compose.runtime.CompositionLocal] for the current content alpha.
 */
val LocalContentAlpha = compositionLocalOf { ContentAlpha.primary }
