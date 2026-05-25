package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

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
import com.sgut.android.nationalfootballleague.di.GameDetailsTopBar
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.*
import com.sgut.android.nationalfootballleague.ui.commoncomps.*
import com.sgut.android.nationalfootballleague.ui.commoncomps.*
import com.sgut.android.nationalfootballleague.ui.commoncomps.PressIconButton
import com.sgut.android.nationalfootballleague.ui.screens.teamdetails.HexToJetpackColor2
import com.sgut.android.nationalfootballleague.utils.formatTo
import com.sgut.android.nationalfootballleague.utils.toDate
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
fun WeightedRows(
    modifier: Modifier,
    header: GameDetailsModel,
    teamColors: GameTeamColors? = null,
) {
    val competition = header.header?.competitions?.firstOrNull() ?: return
    val competitors = competition.competitors
    if (competitors.isEmpty()) return

    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "📊",
            title = "Team Records",
            accentColors = teamColors,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            competitors.forEach { competitor ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        GenericImageLoader(
                            obj = competitor.team?.logos?.getOrNull(0)?.href.orEmpty(),
                            modifier = Modifier.size(32.dp),
                        )
                        Text(
                            text = competitor.team?.abbreviation.orEmpty(),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                    competitor.team?.record?.firstOrNull()?.let { record ->
                        SectionLabel(text = record.summary)
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderTeamLogo(team: GameDetailsTeamInfoModel) {
    GenericImageLoader(
        obj = team.logos.getOrNull(0)?.href ?: "",
        modifier = Modifier.size(60.dp)
    )
}

@Composable
fun HeaderTeamSlot(
    modifier: Modifier,
    competitor: GameDetailsCompetitorModel,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(SIXTEEN.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = competitor.team?.abbreviation ?: "", fontSize = 12.sp)
            Text(text = competitor.record.getOrNull(0)?.summary ?: "", fontSize = 9.sp)
            Text(text = competitor.score.toString(), fontWeight = FontWeight.Bold)
        }

        SpacerDp(modifier = modifier, width = 8)

        HeaderTeamLogo(competitor.team ?: GameDetailsTeamInfoModel())

    }
}

@Composable
fun RightToLeftLayout(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        content()
    }
}

@Composable
fun HeaderStatusSlot(
    modifier: Modifier,
    gameDetailModel: GameDetailsModel,
    teamColors: GameTeamColors? = null,
) {
    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "🗓",
            title = "Schedule",
            accentColors = teamColors,
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 12.dp),
        ) {
            gameDetailModel.header?.competitions?.forEach { competition ->
                HeaderTeamSlot(
                    modifier = modifier,
                    competitor = competition.competitors.first(),
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    SectionLabel(text = competition.date.toDate()?.formatTo("MMM/dd").orEmpty())
                    SectionValue(text = competition.status?.type?.description.orEmpty())
                    Text(
                        text = competition.date.toDate()?.formatTo("K:mm aa").orEmpty(),
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                RightToLeftLayout {
                    HeaderTeamSlot(
                        modifier = modifier,
                        competitor = competition.competitors.last(),
                    )
                }
            }
        }
    }
}

@Composable
fun AthleteNameAndPosition(athlete: GameDetailsAthleteDetailsModel, modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        EnlargeableAthleteImage(
            imageUrl = athlete.headshot?.href ?: "",
            contentDescription = athlete.displayName,
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .border(1.dp, Color.LightGray, CircleShape)
        )
        Column {
            Text(
                text = athlete.displayName,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "#${athlete.jersey}  ${athlete.position?.abbreviation ?: ""}",
                fontSize = 11.sp,
                color = Color.Gray
            )
        }
    }
}
