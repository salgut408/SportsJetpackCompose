package com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.extensions

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

fun TextStyle.emphasize(): TextStyle = copy(fontWeight = FontWeight.SemiBold)

internal fun TextStyle.withDefaultFontFamily(default: FontFamily): TextStyle =
    if (fontFamily != null) this
    else copy(fontFamily = default)
