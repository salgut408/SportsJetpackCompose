package com.sgut.android.nationalfootballleague.ui.commoncomps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style.Theme

@Composable
fun CardHeaderText(
    text: String,
    emoji: String? = null,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        if (!emoji.isNullOrBlank()) {
            Text(text = emoji, fontSize = 22.sp)
        }
        Text(text = text, style = Theme.typography.titleL)
    }
}