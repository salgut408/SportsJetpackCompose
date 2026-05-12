package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsModel
import com.sgut.android.nationalfootballleague.ui.commoncomps.CardHeaderText
import com.sgut.android.nationalfootballleague.ui.commoncomps.EIGHT
import com.sgut.android.nationalfootballleague.ui.commoncomps.NormalDivider
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.DefaultCard
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.SpacerDp
import com.sgut.android.nationalfootballleague.ui.screens.teamdetails.HexToJetpackColor2

@Composable
fun AnimatedCircle(
    gameDetailModel: GameDetailsModel,
    modifier: Modifier = Modifier,
    size: Dp = 200.dp,
    thickness: Dp = 20.dp,

    ) {
    val colors = mutableListOf<Color>()
    val legends = mutableListOf<String>()
    val teams = gameDetailModel.boxscore?.teams

    teams?.map {
        colors.add(HexToJetpackColor2.getColor(it.team?.color ?: ""))
        legends.add(it.team?.name ?: "")
    }

    colors.add(Color.LightGray)
    legends.add("Tie")

    val gameProjection = gameDetailModel.predictor?.awayTeam?.gameProjection ?: 0f
    val teamChanceLoss = gameDetailModel.predictor?.awayTeam?.teamChanceLoss ?: 0f
    val teamChanceTie = gameDetailModel.predictor?.awayTeam?.teamChanceTie ?: 0f
    val values = listOf(gameProjection, teamChanceLoss, teamChanceTie)

    val sumOfValues = values.sum()
    val proportions = values.map { it * 100 / sumOfValues }
    val sweepAngles = proportions.map { it * 360 / 100 }




    val currentState = remember {
        MutableTransitionState(AnimatedCircleProgress.START)
            .apply { targetState = AnimatedCircleProgress.END }
    }
    val transition = rememberTransition(currentState)


    val shift by transition.animateFloat(
        transitionSpec = {
            tween(
                delayMillis = 600,
                durationMillis = 900,
                easing = CubicBezierEasing(0f, 0.75f, 0.35f, 0.85f)
            )
        }, label = ""
    ) { progress ->
        if (progress == AnimatedCircleProgress.START) {
            0f
        } else {
            30f
        }
    }

    DefaultCard(modifier = Modifier) {
        CardHeaderText(text = "Matchup Predictor")
        NormalDivider()
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                text = "$gameProjection%",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp)
            )
            Text(
                text = "$teamChanceLoss%",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
            )
            Box(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .align(Alignment.Center)
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = teams?.last()?.team?.abbreviation ?: "",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    VerticalDivider(
                        color = Color.Black,
                        modifier = modifier.height(100.dp),
                        thickness = 1.dp
                    )
                    Spacer(modifier = modifier.width(8.dp))

                    Text(
                        text = teams?.first()?.team?.abbreviation ?: "",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            Canvas(
                modifier = Modifier
                    .size(size = size)
                    .padding(16.dp)
                    .align(Alignment.Center),
            ) {

                var startAngle = shift -90f


                for (i in values.indices) {

                    drawArc(
                        color = colors.getOrElse(i) { color -> Color.White },
                        startAngle = startAngle + DividerLengthInDegrees,
                        sweepAngle = sweepAngles[i] - DividerLengthInDegrees,
                        useCenter = false,
                        style = Stroke(width = thickness.toPx(), cap = StrokeCap.Butt)
                    )
                    startAngle += sweepAngles[i]
                }
            }
        }// end of box
        SpacerDp(modifier = modifier, height = EIGHT)


        Column() {

            Text(
                text = "Tie: $teamChanceTie%",
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )
            for (i in values.indices) {
                DisplayLegend(
                    color = colors.getOrElse(i, { color -> Color.White }),
                    legend = legends.getOrElse(i) { word -> "" })
            }
        }

    }
}
private enum class AnimatedCircleProgress { START, END }
private const val DividerLengthInDegrees = 1.8f
