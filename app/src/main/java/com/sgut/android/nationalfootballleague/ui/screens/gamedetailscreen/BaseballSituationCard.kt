package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.End
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.InningPrefix
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.Start
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.Probable
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.StatusState
import android.widget.TextView
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.sgut.android.nationalfootballleague.*
import com.sgut.android.nationalfootballleague.ui.commoncomps.GameDetailsTopBar
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.*
import com.sgut.android.nationalfootballleague.ui.commoncomps.*
import com.sgut.android.nationalfootballleague.ui.commoncomps.*
import com.sgut.android.nationalfootballleague.ui.commoncomps.PressIconButton
import com.sgut.android.nationalfootballleague.ui.screens.teamdetails.HexToJetpackColor2
import com.sgut.android.nationalfootballleague.ui.commoncomps.ui_extenstions.formatTo
import com.sgut.android.nationalfootballleague.ui.commoncomps.ui_extenstions.toDate
import java.util.*
import kotlin.math.nextUp
import timber.log.Timber
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.window.Dialog
import java.text.NumberFormat

@Composable
fun BaseballSpecific(
    modifier: Modifier,
    gameDetailSituation: SituationModel,
    gameDetailsModel: GameDetailsModel,
    teamMap: Map<String, GameDetailsAthleteDetailsModel>,
    gameState: StatusState?,
    teamColors: GameTeamColors? = null,
) {
    when (gameState) {
        StatusState.POST -> {
            PostOrPre(gameDetailModel = gameDetailsModel, modifier = modifier)
        }
        StatusState.IN -> {
            BaseballSituation(
                modifier = modifier,
                gameDetailSituation = gameDetailSituation,
                teamMap = teamMap,
                competition = gameDetailsModel.header?.competitions?.firstOrNull()
                    ?: GameDetailsCompetitionModel(),
                teamColors = teamColors,
            )
            SpacerDp(modifier = modifier, height = EIGHT)
            PostOrPre(gameDetailModel = gameDetailsModel, modifier = modifier)
        }
        StatusState.PRE -> {
            PostOrPre(gameDetailModel = gameDetailsModel, modifier = modifier)
        }
        else -> {}
    }

    if (gameDetailsModel.rosters.isNotEmpty()) {
        SpacerDp(modifier = modifier, height = EIGHT)
        BaseballRosterCard(modifier = modifier, rosters = gameDetailsModel.rosters)
    }
}

@Composable
fun PostOrPre(gameDetailModel: GameDetailsModel, modifier: Modifier) {
    AnimatedCircle(
        modifier = modifier,
        gameDetailModel = gameDetailModel
    )
    SpacerDp(modifier = modifier, height = EIGHT)
    ProbablesList(list = gameDetailModel.header?.competitions?.getOrNull(0)?.competitors ?: listOf()
        ?: listOf(),
        modifier = modifier)

}

@Composable
fun ProbablesList(list: List<GameDetailsCompetitorModel>, modifier: Modifier) {
    val home = list.getOrNull(0) ?: return
    val away = list.getOrNull(1) ?: return
    val homeColor = home.team?.color?.let { HexToJetpackColor2.getColor(it) } ?: Color.DarkGray
    val awayColor = away.team?.color?.let { HexToJetpackColor2.getColor(it) } ?: Color.Gray
    val homeProbable = home.probables.getOrNull(0)
    val awayProbable = away.probables.getOrNull(0)

    DefaultCard(modifier = modifier) {
        CardHeaderText(text = "Probable Starters")
        NormalDivider()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
        ) {
            // Diagonal split background
            Canvas(modifier = Modifier.fillMaxSize()) {
                val diagStart = size.width * 0.44f
                val diagEnd   = size.width * 0.56f

                val leftPath = Path().apply {
                    moveTo(0f, 0f)
                    lineTo(diagEnd, 0f)
                    lineTo(diagStart, size.height)
                    lineTo(0f, size.height)
                    close()
                }
                drawPath(leftPath, homeColor)

                val rightPath = Path().apply {
                    moveTo(diagEnd, 0f)
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height)
                    lineTo(diagStart, size.height)
                    close()
                }
                drawPath(rightPath, awayColor)
            }

            // Content row over the diagonal background
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Home side
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    EnlargeableAthleteImage(
                        imageUrl = homeProbable?.athlete?.headshot?.href ?: "",
                        contentDescription = homeProbable?.athlete?.displayName,
                        modifier = Modifier.size(64.dp).clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                    )
                    Text(
                        text = home.team?.abbreviation ?: "",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )
                    Text(
                        text = homeProbable?.athlete?.displayName ?: "TBD",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White.copy(alpha = 0.9f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.widthIn(max = 120.dp)
                    )
                }

                // VS badge
                Text(
                    text = "VS",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.6f),
                            offset = Offset(1f, 1f),
                            blurRadius = 4f
                        )
                    )
                )

                // Away side
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    EnlargeableAthleteImage(
                        imageUrl = awayProbable?.athlete?.headshot?.href ?: "",
                        contentDescription = awayProbable?.athlete?.displayName,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                    )
                    Text(
                        text = away.team?.abbreviation ?: "",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White,
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = awayProbable?.athlete?.displayName ?: "TBD",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White.copy(alpha = 0.9f),
                        textAlign = TextAlign.End,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.widthIn(max = 120.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BaseballSituation(
    modifier: Modifier,
    gameDetailSituation: SituationModel,
    competition: GameDetailsCompetitionModel,
    teamMap: Map<String, GameDetailsAthleteDetailsModel>,
    teamColors: GameTeamColors? = null,
) {
    val isEndOfInning = competition.status?.periodPrefix == InningPrefix.END
    val inningDetail = competition.status?.type?.gameTimeDetail ?: ""
    val onFirst = gameDetailSituation.onFirst?.playerId != null
    val onSecond = gameDetailSituation.onSecond?.playerId != null
    val onThird = gameDetailSituation.onThird?.playerId != null

    DefaultCard(modifier = modifier.fillMaxWidth()) {
        CardSectionHeader(
            emoji = "⚾",
            title = "Live Situation",
            subtitle = inningDetail.takeIf { it.isNotBlank() },
            accentColors = teamColors,
        )

        // Header: inning info + outs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "CURRENT SITUATION",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    letterSpacing = 1.5.sp
                )
                Text(
                    text = inningDetail,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            // Outs indicator
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "OUTS",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    letterSpacing = 1.5.sp
                )
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    repeat(3) { idx ->
                        val filled = idx < gameDetailSituation.outs
                        Canvas(modifier = Modifier.size(14.dp)) {
                            drawCircle(
                                color = if (filled) Color(0xFFE53935) else Color(0xFFBDBDBD),
                                radius = size.minDimension / 2f
                            )
                        }
                    }
                }
            }
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))

        // Count row: Balls + Strikes
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Balls
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "B",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF43A047),
                    letterSpacing = 1.sp
                )
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    repeat(4) { idx ->
                        val filled = idx < gameDetailSituation.balls
                        Canvas(modifier = Modifier.size(13.dp)) {
                            drawCircle(
                                color = if (filled) Color(0xFF43A047) else Color(0xFFBDBDBD),
                                radius = size.minDimension / 2f
                            )
                        }
                    }
                }
            }
            // Strikes
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "S",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF9A825),
                    letterSpacing = 1.sp
                )
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    repeat(3) { idx ->
                        val filled = idx < gameDetailSituation.strikes
                        Canvas(modifier = Modifier.size(13.dp)) {
                            drawCircle(
                                color = if (filled) Color(0xFFF9A825) else Color(0xFFBDBDBD),
                                radius = size.minDimension / 2f
                            )
                        }
                    }
                }
            }
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))

        // Baseball diamond + batter/pitcher section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Batter or Due Up column
            if (isEndOfInning) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "DUE UP",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        letterSpacing = 1.5.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        gameDetailSituation.dueUp.take(3).forEach { dueUpItem ->
                            val player = teamMap[dueUpItem.playerId]
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                EnlargeableAthleteImage(
                                    imageUrl = player?.headshot?.href ?: "",
                                    contentDescription = player?.displayName,
                                    modifier = Modifier
                                        .size(52.dp)
                                        .clip(CircleShape)
                                        .border(2.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), CircleShape)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = player?.shortName ?: "TBD",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = dueUpItem.batOrder,
                                    fontSize = 9.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                )
                            }
                        }
                    }
                }
            } else {
                // Batter
                val batter = teamMap[gameDetailSituation.batter?.playerId?.toString()]
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "AT BAT",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF43A047),
                        letterSpacing = 1.5.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    EnlargeableAthleteImage(
                        imageUrl = batter?.headshot?.href ?: "",
                        contentDescription = batter?.displayName,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color(0xFF43A047), CircleShape)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = batter?.shortName ?: "—",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = batter?.position?.abbreviation ?: "",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                }
            }

            // Baseball Diamond (canvas)
            BaseballDiamond(
                onFirst = onFirst,
                onSecond = onSecond,
                onThird = onThird,
                modifier = Modifier.size(110.dp)
            )

            // Pitcher
            if (!isEndOfInning) {
                val pitcher = teamMap[gameDetailSituation.pitcher?.playerId?.toString()]
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "PITCHING",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE53935),
                        letterSpacing = 1.5.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    EnlargeableAthleteImage(
                        imageUrl = pitcher?.headshot?.href ?: "",
                        contentDescription = pitcher?.displayName,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color(0xFFE53935), CircleShape)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = pitcher?.shortName ?: "—",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = pitcher?.position?.abbreviation ?: "",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }

        // Runners on base labels
        if (!isEndOfInning && (onFirst || onSecond || onThird)) {
            HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Top
            ) {
                listOf(
                    Triple("1ST", gameDetailSituation.onFirst?.playerId, onFirst),
                    Triple("2ND", gameDetailSituation.onSecond?.playerId, onSecond),
                    Triple("3RD", gameDetailSituation.onThird?.playerId, onThird)
                ).filter { it.third }.forEach { (label, playerId, _) ->
                    val runner = teamMap[playerId.toString()]
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .background(Color(0xFFF9A825), RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = label,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                        }
                        EnlargeableAthleteImage(
                            imageUrl = runner?.headshot?.href ?: "",
                            contentDescription = runner?.displayName,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .border(1.dp, Color(0xFFF9A825), CircleShape)
                        )
                        Text(
                            text = runner?.shortName ?: "Runner",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }

        // Last play
        val lastPlayText = gameDetailSituation.lastPlay?.text
        if (!lastPlayText.isNullOrBlank()) {
            HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
            Text(
                text = lastPlayText,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )
        }
    }
}

@Composable
fun BaseballDiamond(
    onFirst: Boolean,
    onSecond: Boolean,
    onThird: Boolean,
    modifier: Modifier = Modifier,
) {
    val baseColor = Color(0xFFF9A825)
    val emptyColor = Color(0xFFBDBDBD)
    val homeColor = Color(0xFF757575)

    // Per-base fill animations: each base's "filled" alpha lerps to its current
    // boolean state, so a runner reaching a base appears to slide-in rather
    // than snap. animateFloatAsState re-runs whenever the boolean flips,
    // which is exactly when ESPN reports a new on-base situation.
    val firstFill by animateFloatAsState(
        targetValue = if (onFirst) 1f else 0f,
        animationSpec = tween(durationMillis = 350),
        label = "base-first-fill",
    )
    val secondFill by animateFloatAsState(
        targetValue = if (onSecond) 1f else 0f,
        animationSpec = tween(durationMillis = 350),
        label = "base-second-fill",
    )
    val thirdFill by animateFloatAsState(
        targetValue = if (onThird) 1f else 0f,
        animationSpec = tween(durationMillis = 350),
        label = "base-third-fill",
    )

    // Subtle pulsing scale on any base that's currently occupied — keeps the
    // diamond feeling "alive" during live games without being distracting.
    val pulseTransition = rememberInfiniteTransition(label = "base-pulse")
    val pulseScale by pulseTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 900, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "base-pulse-scale",
    )

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val cx = w / 2f
        val cy = h / 2f

        // Diamond corners: home=bottom, first=right, second=top, third=left
        val homeX = cx; val homeY = h * 0.85f
        val firstX = w * 0.85f; val firstY = cy
        val secondX = cx; val secondY = h * 0.15f
        val thirdX = w * 0.15f; val thirdY = cy

        val baseSizeRatio = 0.10f
        val bw = w * baseSizeRatio
        val bh = h * baseSizeRatio

        fun drawAnimatedBase(centerX: Float, centerY: Float, fillFraction: Float) {
            // Resting size always drawn (empty base outline) underneath; the
            // filled base overlays at a slightly larger scale when occupied.
            val pulse = if (fillFraction > 0.5f) pulseScale else 1.0f
            val emptyPath = Path().apply {
                moveTo(centerX, centerY - bh)
                lineTo(centerX + bw, centerY)
                lineTo(centerX, centerY + bh)
                lineTo(centerX - bw, centerY)
                close()
            }
            drawPath(emptyPath, color = emptyColor)

            if (fillFraction > 0f) {
                val pbw = bw * pulse
                val pbh = bh * pulse
                val filledPath = Path().apply {
                    moveTo(centerX, centerY - pbh)
                    lineTo(centerX + pbw, centerY)
                    lineTo(centerX, centerY + pbh)
                    lineTo(centerX - pbw, centerY)
                    close()
                }
                drawPath(filledPath, color = baseColor.copy(alpha = fillFraction))
            }
        }

        // Draw baselines
        val lineColor = emptyColor.copy(alpha = 0.4f)
        drawLine(lineColor, Offset(homeX, homeY), Offset(firstX, firstY), strokeWidth = 2f)
        drawLine(lineColor, Offset(firstX, firstY), Offset(secondX, secondY), strokeWidth = 2f)
        drawLine(lineColor, Offset(secondX, secondY), Offset(thirdX, thirdY), strokeWidth = 2f)
        drawLine(lineColor, Offset(thirdX, thirdY), Offset(homeX, homeY), strokeWidth = 2f)

        // Draw bases (with animated fills + pulse on occupied)
        drawAnimatedBase(secondX, secondY, secondFill)
        drawAnimatedBase(firstX, firstY, firstFill)
        drawAnimatedBase(thirdX, thirdY, thirdFill)

        // Home plate
        val hp = Path().apply {
            moveTo(homeX, homeY - bh * 0.8f)
            lineTo(homeX + bw * 0.8f, homeY)
            lineTo(homeX + bw * 0.5f, homeY + bh * 0.6f)
            lineTo(homeX - bw * 0.5f, homeY + bh * 0.6f)
            lineTo(homeX - bw * 0.8f, homeY)
            close()
        }
        drawPath(hp, color = homeColor)
    }
}

@Composable
fun EnlargeableAthleteImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    var enlarged by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures(onLongPress = { enlarged = true })
        }
    ) {
        GenericImageLoader(obj = imageUrl, modifier = Modifier.fillMaxSize())
    }

    if (enlarged) {
        Dialog(onDismissRequest = { enlarged = false }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .clickable { enlarged = false }
                    .padding(bottom = 16.dp)
            ) {
                GenericImageLoader(
                    obj = imageUrl,
                    modifier = Modifier
                        .size(280.dp)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                )
                contentDescription?.takeIf { it.isNotEmpty() }?.let {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = it,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
            }
        }
    }
}
