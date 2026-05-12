package com.sgut.android.nationalfootballleague.ui.screens.videos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun ShowGameVideos() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            TextOne(
                modifier = Modifier
//                    .weight(weight = 1f, fill = true)
                    .background(Color.Yellow)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Row() {
                TextTwo(
                    modifier = Modifier
                        .background(Color.Green)
                )
                TextTwo(
                    modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(weight = 1f, fill = true)
                        .background(Color.Cyan)
                )

            }
        }

    }
}

@Composable
fun TextOne(modifier: Modifier) {
    Row(modifier = modifier) {
        Text(
            text = "IMg",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(
            text = "TeamComm",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun TextTwo(modifier: Modifier) {
    Box(modifier = modifier) {
        Text(text = "Gootbuye")
    }
}