package com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.tokens

import androidx.compose.ui.unit.dp
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style.ContentAlpha

internal object SwitchTokens {
    val UncheckedThumbColor = ColorSchemeKeyTokens.PrimaryAccent
    val UncheckedThumbOpacity = ContentAlpha.selection
    val UncheckedTrackColor = ColorSchemeKeyTokens.PrimaryAccent
    val UncheckedTrackOpacity = ContentAlpha.border
    val UncheckedIconColor = ColorSchemeKeyTokens.PrimaryAccent

    val CheckedThumbColor = ColorSchemeKeyTokens.PrimaryAccent
    val CheckedThumbOpacity = ContentAlpha.primary
    val CheckedTrackColor = ColorSchemeKeyTokens.PrimaryAccent
    val CheckedTrackOpacity = ContentAlpha.border
    val CheckedIconColor = ColorSchemeKeyTokens.OnPrimaryAccent

    val DisabledUncheckedThumbColor = ColorSchemeKeyTokens.PrimaryAccent
    val DisabledUncheckedThumbOpacity = ContentAlpha.border
    val DisabledUncheckedTrackColor = ColorSchemeKeyTokens.PrimaryAccent
    val DisabledUncheckedTrackOpacity = ContentAlpha.highlight
    val DisabledUncheckedIconColor = ColorSchemeKeyTokens.PrimaryAccent

    val DisabledCheckedThumbColor = ColorSchemeKeyTokens.PrimaryAccent
    val DisabledCheckedThumbOpacity = ContentAlpha.border
    val DisabledCheckedTrackColor = ColorSchemeKeyTokens.PrimaryAccent
    val DisabledCheckedTrackOpacity = ContentAlpha.highlight
    val DisabledCheckedIconColor = ColorSchemeKeyTokens.OnPrimaryAccent

    val TrackWidth = 50.dp
    val TrackHeight = 28.dp

    val ThumbDiameter = 24.dp
    val ThumbPadding = (TrackHeight / 2) - (ThumbDiameter / 2)
    val ThumbOffset = TrackWidth - ThumbDiameter - (ThumbPadding * 2)
}
