package com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ButtonWithState() {

}


@Composable
fun BasicTextButton(
    @StringRes text: Int,
    modifier: Modifier,
    action: () -> Unit,
) {
    TextButton(
        onClick = action,
        modifier = modifier
    ) {
        Text(
            text = stringResource(text)
        )
    }
}

@Composable
fun BasicButtonToNavigate(
    @StringRes text: Int,
    modifier: Modifier,
    sport: String,
    league: String,
    onNavigateTo: (sport: String, league: String) -> Unit
) {
    val onNavTo = {onNavigateTo(sport, league)}
    Button(
        onClick = onNavTo,
        modifier = modifier,
        colors =
        ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        Text(text = stringResource(text), fontSize = 16.sp)
    }
}

@Composable
fun BasicButton(
    @StringRes text: Int,
    modifier: Modifier,
    action: () -> Unit,
) {
    Button(
        onClick = action,
        modifier = modifier,
        colors =
        ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        Text(text = stringResource(text), fontSize = 16.sp)
    }
}

@Composable
fun DialogConfirmButton(
    @StringRes text: Int,
    action: () -> Unit,
) {
    Button(
        onClick = action,
        colors =
        ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        Text(text = stringResource(text))
    }
}

@Composable
fun DialogCancelButton(
    @StringRes text: Int,
    action: () -> Unit,
) {
    Button(
        onClick = action,
        colors =
        ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onPrimary,
            contentColor = MaterialTheme.colors.primary
        )
    ) {
        Text(
            text = stringResource(text)
        )
    }
}

@Composable
fun PressIconButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    isPressed: Boolean,
) {


    Button(
        onClick = onClick,
        modifier = modifier,
        colors =
        ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onPrimary,
            contentColor = MaterialTheme.colors.primary
        ),
        interactionSource = remember { MutableInteractionSource() }
    ) {
        AnimatedVisibility(visible = isPressed) {
            if (isPressed) {
                Row {
                    icon()
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                }
            }
        }
        text()
    }
}


@Composable
fun NewButton(text: @Composable () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val rippleColor = Color.Red
    val shape = RoundedCornerShape(size = 16.dp)

    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .clip(shape = shape)
            .indication(
                interactionSource = interactionSource,
                indication = rememberRipple(
                    color = rippleColor,
                    radius = 90.dp
                )
            )
            .height(height = 50.dp),
        shape = shape,
        interactionSource = interactionSource
    ) {
        text()
    }
}


//private object RippleCustomTheme: RippleTheme {
//    override fun defaultColor() = RippleTheme.defaultRippleColor(Color.Cyan, lightTheme = true)
//
//    override fun rippleAlpha(): RippleAlpha =
//        RippleTheme.defaultRippleAlpha(Color.Blue, lightTheme = true)
//
//}

