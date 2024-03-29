package com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sgut.android.nationalfootballleague.ui.commoncomps.SIXTEEN
import com.sgut.android.nationalfootballleague.ui.theme.NationalFootballLeagueTheme
import com.sgut.android.nationalfootballleague.utils.dropdownSelector


@ExperimentalMaterialApi
@Composable
fun DangerousCardEditor(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    content: String,
    modifier: Modifier,
    onEditClick: () -> Unit
) {
    CardEditor(title, icon, content, onEditClick, MaterialTheme.colors.primary, modifier)
}

@ExperimentalMaterialApi
@Composable
fun RegularCardEditor(
    @StringRes title: Int,
    content: String,
    @DrawableRes icon: Int,
    modifier: Modifier,
    onEditClick: () -> Unit
) {
    CardEditor(title, icon, content, onEditClick, MaterialTheme.colors.onSurface, modifier)
}

@ExperimentalMaterialApi
@Composable
private fun CardEditor(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    content: String,
    onEditClick: () -> Unit,
    highlightColor: Color,
    modifier: Modifier
) {
    Card(
        backgroundColor = MaterialTheme.colors.onPrimary,
        modifier = modifier,
        onClick = onEditClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    stringResource(title),
                    color = highlightColor
                ) }

            if (content.isNotBlank()) {
                Text(
                    text = content,
                    modifier = Modifier.padding(16.dp, 0.dp)
                )
            }

            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Icon",
                tint = highlightColor
            )
        }
    }
}

@Composable
@ExperimentalMaterialApi
fun CardSelector(
    @StringRes label: Int,
    options: List<String>,
    selection: String,
    modifier: Modifier,
    onNewValue: (String) -> Unit
) {
    Card(
        backgroundColor = MaterialTheme.colors.onPrimary,
        modifier = modifier
    ) {
        DropdownSelector(
            label,
            options,
            selection,
            Modifier.dropdownSelector(),
            onNewValue
        )
    }
}

@Composable
fun DefaultCard(
    modifier: Modifier,
    color: Color = Color.LightGray,
    contentColor: Color = Color.Black,
    content: @Composable () -> Unit,
) {
    Card(
        backgroundColor = color,
        contentColor = contentColor,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = modifier
                .padding(start = SIXTEEN.dp, end = SIXTEEN.dp)
        ) {
            content()
        }
    }
}



@Composable
fun SportCard(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    color: Color = Color.LightGray,
    contentColor: Color = Color.Black,
    border: BorderStroke? = null,
    content: @Composable () -> Unit,
    ) {
    SportSurface(
        modifier = modifier,
        shape = shape,
        color = color,
        contentColor = contentColor,
        border = border,
        content = content
    )
}

@Preview
@Composable
fun CardPreview() {
    NationalFootballLeagueTheme{
        SportCard() {
            Text(text = "Demo",)

        }
    }
}