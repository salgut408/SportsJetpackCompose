package com.sgut.android.nationalfootballleague.ui.commoncomps

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PressIconButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    isPressed: Boolean,
    modifier: Modifier = Modifier,
) = Button(
    onClick = onClick,
    modifier = modifier,
    colors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.onPrimary,
        contentColor = MaterialTheme.colorScheme.primary,
    ),
) {
    AnimatedVisibility(visible = isPressed) {
        Row {
            icon()
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        }
    }
    text()
}