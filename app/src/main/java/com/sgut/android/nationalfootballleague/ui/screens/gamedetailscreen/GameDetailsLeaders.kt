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
fun SeasonLeaders(
    modifier: Modifier,
    leaders: List<GameDetailsLeadersModel>,
    teamColors: GameTeamColors? = null,
) {
    if (leaders.isEmpty()) return
    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "⭐",
            title = "Season Leaders",
            subtitle = "Top performers from each team",
            accentColors = teamColors,
        )
        Column(modifier = Modifier.padding(bottom = 10.dp)) {
            leaders.forEachIndexed { teamIndex, teamLeaders ->
                if (teamIndex > 0) {
                    androidx.compose.material3.HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant,
                        thickness = 0.5.dp,
                        modifier = Modifier.padding(vertical = 10.dp),
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    GenericImageLoader(
                        obj = teamLeaders.team.logo,
                        modifier = Modifier.size(28.dp),
                    )
                    Text(
                        text = teamLeaders.team.abbreviation,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
                teamLeaders.leaders.forEach { category ->
                    SectionLabel(
                        text = category.displayName,
                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    )
                    category.leadersAthlete.forEach { athlete ->
                        SeasonLeadersPlayer(athlete = athlete)
                    }
                }
            }
        }
    }
}

@Composable
fun TabsSeasonLeaders(
    modifier: Modifier,
    leaders: List<GameDetailsLeadersModel>,
    teamColors: GameTeamColors? = null,
) {
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabTitles = listOf(
        leaders.getOrNull(0)?.team,
        leaders.getOrNull(1)?.team,
    )

    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "🏆",
            title = "Top Performers",
            accentColors = teamColors,
        )
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ) {
            tabTitles.forEachIndexed { index, team ->
                Tab(
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            GenericImageLoader(
                                obj = team?.logo.toString(),
                                modifier = Modifier.size(24.dp),
                            )
                            Text(
                                text = team?.abbreviation.orEmpty(),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.sp,
                            )
                        }
                    },
                )
            }
        }
        Column(modifier = Modifier.padding(bottom = 8.dp)) {
            when (tabIndex) {
                0 -> Leads(leaders = leaders.getOrNull(0)?.leaders ?: listOf(), teamInt = 0)
                1 -> Leads(leaders = leaders.getOrNull(1)?.leaders ?: listOf(), teamInt = 1)
            }
        }
    }
}

@Composable
fun Leads(
    leaders: List<GameLeadersModel>,
    teamInt: Int,
) {
    if (leaders.isEmpty()) return
    Column {
        leaders.forEach { category ->
            val topAthlete = category.leadersAthlete.firstOrNull() ?: return@forEach
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                EnlargeableAthleteImage(
                    imageUrl = topAthlete.athlete.headshot?.href ?: "",
                    contentDescription = topAthlete.athlete.displayName,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.LightGray, CircleShape)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = category.displayName.uppercase(),
                        fontSize = 9.sp,
                        color = Color.Gray,
                        letterSpacing = 0.6.sp
                    )
                    Text(
                        text = topAthlete.athlete.displayName,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = topAthlete.athlete.position?.abbreviation ?: "",
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }
                Text(
                    text = topAthlete.displayValue,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f), thickness = 0.5.dp)
        }
    }
}

@Composable
fun SeasonLeadersPlayer(athlete: AthleteLeaderModel) {
    val athleteNavigator = com.sgut.android.nationalfootballleague.ui.navigation.LocalAthleteNavigator.current
    val sportLeague = com.sgut.android.nationalfootballleague.ui.navigation.LocalSportLeague.current
    val athleteId = athlete.athlete.id

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (athleteId.isNotBlank()) {
                    Modifier.clickable {
                        athleteNavigator(athleteId, sportLeague.sport, sportLeague.league)
                    }
                } else Modifier
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        EnlargeableAthleteImage(
            imageUrl = athlete.athlete.headshot?.href ?: "",
            contentDescription = athlete.athlete.displayName,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .border(1.dp, Color.LightGray, CircleShape)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = athlete.athlete.displayName,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = athlete.athlete.position?.abbreviation ?: "",
                fontSize = 11.sp,
                color = Color.Gray
            )
        }
        Text(
            text = athlete.displayValue,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
