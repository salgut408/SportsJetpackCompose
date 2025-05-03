package com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.tokens

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style.ContentAlpha


internal object TextFieldTokens {
    val BackgroundColor = ColorSchemeKeyTokens.Surface
    val CursorColor = ColorSchemeKeyTokens.PrimaryAccent
    val FocusedIndicatorColor = ColorSchemeKeyTokens.PrimaryAccent
    val ErrorColor = ColorSchemeKeyTokens.Error
    val SelectionOpacity = ContentAlpha.selection
    val DisabledContentOpacity = ContentAlpha.disabled
    val DisabledLabelOpacity = ContentAlpha.disabled
    val IndicatorOpacity = ContentAlpha.border


    val MinimumHeight = 64.dp
    val ScrimLength = MeasurementTokens.Spacing.Large
    val TopPadding = MeasurementTokens.Spacing.Medium
    val HorizontalPadding = MeasurementTokens.Spacing.Medium
    val AuxiliaryPadding = MeasurementTokens.Spacing.Small

    val CornerRadius = MeasurementTokens.CornerRadius.Medium
    val Shape = RoundedCornerShape(CornerRadius)
}
