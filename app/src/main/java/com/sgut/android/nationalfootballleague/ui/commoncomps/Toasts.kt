package com.sgut.android.nationalfootballleague.ui.commoncomps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomToast(message: String) {
    Box(
        modifier = Modifier
            .background(Color.Red)
            .padding(16.dp)
    ) {
        Text(
            text = message,
            color = Color.White,
            fontSize = 20.sp
        )
    }
}