package com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.tokens

import androidx.compose.ui.unit.dp

internal object MeasurementTokens {

    object Spacing {
        private val Base = 2.dp

        val XXXSmall = Base
        val XXSmall = XXXSmall * 2
        val XSmall = XXXSmall * 3
        val Small = XXSmall * 2
        val Medium = XXSmall * 3
        val Large = Small * 2
        val XLarge = Medium * 2
        val XXLarge = Small * 4
    }

    object CornerRadius {
        private val Base = 2.dp

        val XSmall = Base
        val Small = XSmall * 2
        val Medium = Small * 2
        val Large = Medium * 2
        val XLarge = Medium * 3
    }

    val ListTitleGapSpacing = Spacing.Medium
    val ListItemInfoSpacing = Spacing.Small
    val StatusIconSpacing = Spacing.Small
}

