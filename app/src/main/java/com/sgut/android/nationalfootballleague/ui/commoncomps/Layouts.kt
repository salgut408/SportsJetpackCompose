package com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun VerticalGrid(
    modifier: Modifier = Modifier,
    columns: Int = 2,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier,
    ) { measurables, constraints ->
        val itemWidth = constraints.maxWidth / columns
        val itemConstraints = constraints.copy(minWidth = itemWidth, maxWidth = itemWidth)
        val placeables = measurables.map { it.measure(itemConstraints) }
        val columnHeights = Array(columns) { 0 }
        placeables.forEachIndexed { index, placeable ->
            val column = index % columns
            columnHeights[column] += placeable.height
        }
        val height = (columnHeights.maxOrNull() ?: constraints.minHeight).coerceAtMost(constraints.maxHeight)
        layout(
            width = constraints.maxWidth,
            height = height,
        ) {
            val columnY = Array(columns) {0}
            placeables.forEachIndexed { index, placeable ->
                val column = index % columns
                placeable.placeRelative(
                    x = column * itemWidth,
                    y = columnY[column]
                )
                columnY[column] += placeable.height
            }
        }
    }

}
//reusable Spacer you can input for height or width
@Composable
fun SpacerDp(
    modifier: Modifier,
    height: Int =  0,
    width: Int = 0
) {
   Spacer(
       modifier = modifier
       .height(height.dp)
           .width(width.dp)
   )
}




@Composable
fun HeadingSection(modifier: Modifier = Modifier, title: String, subtitle: String, content: @Composable ()-> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = title, style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp))
        Text(text = subtitle, style = MaterialTheme.typography.titleSmall)
        HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
        content()
    }
}