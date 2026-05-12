package com.sgut.android.nationalfootballleague.ui.commoncomps

import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NormalDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.DarkGray,
    thickness: Dp = 1.dp,
    startIndent: Dp = 0.dp
) {
    HorizontalDivider(
        modifier = modifier,
        thickness = thickness,
        color = color
    )

}