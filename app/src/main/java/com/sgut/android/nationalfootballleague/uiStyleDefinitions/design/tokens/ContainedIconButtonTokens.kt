package com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.tokens

import androidx.compose.ui.unit.dp
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style.ContentAlpha

internal object ContainedIconButtonTokens {
    val ContainerColor = ColorSchemeKeyTokens.Surface
    val ContentColor = ColorSchemeKeyTokens.OnSurface

    val BorderOpacity = ContentAlpha.border
    val DisabledBorderOpacity = ContentAlpha.border / 2
    val DisabledContentOpacity = ContentAlpha.disabled

    val Size = 48.dp
    val ContainerPadding = 8.dp
    val RippleRadius = 21.dp
}
