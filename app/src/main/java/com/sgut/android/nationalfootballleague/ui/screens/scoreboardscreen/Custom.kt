package com.sgut.android.nationalfootballleague.ui.screens.scoreboardscreen

import android.util.LayoutDirection
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import kotlin.math.min



@Composable
fun NewUi() {
    Canvas(modifier = Modifier
        .size(300.dp)
        .border(color = Color.Blue, width = 3.dp),
    ){
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawLine( start = Offset(x = canvasWidth, y = 0f),
            end = Offset(x = 0f, y = canvasHeight),
            color = Color.Blue)
    }

}


@Composable
fun OtherUi() {
    Box(
        modifier = Modifier
            .width(300.dp)
            .drawBehind {
                drawRect(
                    color = Color.Cyan,
//                    topLeft = Offset(x = 0f, y = 100.dp.toPx()),
                    size = Size(150.dp.toPx(), 350.dp.toPx())
                )
            }
    ) {


    }
}


@Composable
fun CustomLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    progressColor: Color = Color.Blue,
    backgroundColor: Color = Color.Blue.copy(0.24f),
    clipShape: Shape = RoundedCornerShape(16.dp)
) {
    Box(
        modifier = modifier
            .clip(clipShape)
            .background(backgroundColor)
            .height(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(progressColor)
                .fillMaxHeight()
                .fillMaxWidth(progress)
        )
    }
}




@Composable
 fun ProgressPie(progress: Float){
    Column(modifier=Modifier.padding(10.dp)) {
        var progress by remember { mutableStateOf(progress) }
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .aspectRatio(1f)
                .drawBehind {
                    drawCircle(Color.LightGray, style = Stroke(1.dp.toPx()))
                    drawArc(
                        Color.Blue,
                        startAngle = -90f,
                        sweepAngle = progress,
                        useCenter = true
                    )
                }
        )

        Slider(
            value = progress,
            onValueChange = {
                progress = it
            },
            valueRange = 0f..360f
        )
    }
 }
