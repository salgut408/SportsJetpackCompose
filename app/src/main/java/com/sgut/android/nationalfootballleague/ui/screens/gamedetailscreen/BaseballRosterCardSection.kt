package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.End
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

private val BASEBALL_BATTING_STATS = setOf("AB", "H", "RBI", "SB", "BB", "SO", "HR", "AVG", "OBP", "SLG", "OPS", "R", "2B", "3B")

private val BASEBALL_PITCHING_STATS = setOf("IP", "H", "R", "ER", "BB", "SO", "HR", "ERA", "PC-ST", "BF")

@Composable
fun BaseballRosterCard(
    modifier: Modifier = Modifier,
    rosters: List<RostersModel>,
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    DefaultCard(modifier = modifier.fillMaxWidth()) {
        Column {
            // Team tabs
            TabRow(selectedTabIndex = selectedTab) {
                rosters.forEachIndexed { index, rosterEntry ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                GenericImageLoader(
                                    obj = rosterEntry.team.logos,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                )
                                Text(
                                    text = rosterEntry.team.abbreviation,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    )
                }
            }

            // Roster list for selected team
            rosters.getOrNull(selectedTab)?.let { rosterEntry ->
                // Header row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "#",
                        modifier = Modifier.width(28.dp),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "PLAYER",
                        modifier = Modifier.weight(1f),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "POS",
                        modifier = Modifier.width(40.dp),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        textAlign = TextAlign.End
                    )
                }
                HorizontalDivider()

                rosterEntry.roster
                    .sortedBy { it.batOrder.takeIf { o -> o > 0 } ?: Int.MAX_VALUE }
                    .forEach { player ->
                        BaseballRosterPlayerRow(player = player)
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    }
            }
        }
    }
}

@Composable
fun BaseballRosterPlayerRow(
    player: RosterModel,
) {
    var expanded by remember { mutableStateOf(false) }

    val athleteNavigator = com.sgut.android.nationalfootballleague.ui.navigation.LocalAthleteNavigator.current
    val sportLeague = com.sgut.android.nationalfootballleague.ui.navigation.LocalSportLeague.current
    val athleteId = player.athlete.id

    val battingStats = player.stats.filter { it.abbreviation in BASEBALL_BATTING_STATS }
    val pitchingStats = player.stats.filter { it.abbreviation in BASEBALL_PITCHING_STATS }
    val displayStats = battingStats.ifEmpty { pitchingStats }
    val hasStats = displayStats.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = hasStats) { expanded = !expanded }
            .animateContentSize(animationSpec = tween(200))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Bat order badge
            Text(
                text = if (player.batOrder > 0) player.batOrder.toString() else "",
                modifier = Modifier.width(28.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )

            // Headshot — tap opens the athlete page (row tap toggles stats).
            GenericImageLoader(
                obj = player.athlete.headshot?.href ?: "",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .then(
                        if (athleteId.isNotBlank()) {
                            Modifier.clickable {
                                athleteNavigator(athleteId, sportLeague.sport, sportLeague.league)
                            }
                        } else Modifier
                    )
            )

            Spacer(modifier = Modifier.width(10.dp))

            // Name + position
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = player.athlete.displayName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = player.position.displayName,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
                )
            }

            // Position abbreviation + expand indicator
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = player.position.abbreviation,
                    modifier = Modifier.width(32.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.End
                )
                if (hasStats) {
                    Text(
                        text = if (expanded) "▴" else "▾",
                        modifier = Modifier.padding(start = 6.dp),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                    )
                }
            }
        }

        // Expanded stats
        if (expanded && hasStats) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
            ) {
                Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                    Text(
                        text = if (battingStats.isNotEmpty()) "BATTING STATS" else "PITCHING STATS",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        letterSpacing = 1.sp,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    // Stats in a wrapping row grid
                    val chunked = displayStats.chunked(3)
                    chunked.forEach { rowStats ->
                        Row(modifier = Modifier.fillMaxWidth()) {
                            rowStats.forEach { stat ->
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(vertical = 4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = stat.displayValue,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = stat.abbreviation,
                                        fontSize = 10.sp,
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
                                        letterSpacing = 0.5.sp
                                    )
                                }
                            }
                            // Fill remaining columns if row is not full
                            repeat(3 - rowStats.size) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
    }
}
