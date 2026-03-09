package com.sgut.android.nationalfootballleague.ui.screens.teamdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sgut.android.nationalfootballleague.di.ToolBar2
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.RecordModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.*
import com.sgut.android.nationalfootballleague.utils.formatTo
import com.sgut.android.nationalfootballleague.utils.toDate

//TODO only pass what is needed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDetailScreen(
    modifier: Modifier = Modifier,
    teamDetailViewModel: TeamDetailViewModel = hiltViewModel(),
    team: String,
    sport: String,
    league: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    ) {
    teamDetailViewModel.getFullTeamDetails(team, sport, league)

    val teamDetailUiState by teamDetailViewModel.teamDetailUiState.collectAsStateWithLifecycle()
    val teamDetail = teamDetailUiState.currentTeamDetails
    val teamSchedule = teamDetailUiState.schedule
    val roster = teamDetailUiState.atheletes
    val stats = teamDetailUiState.stats
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior (rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ToolBar2(
                title = teamDetail.displayName,
                canNavigateBack = canNavigateBack,
                navigateUp = navigateUp,
                scrollBehavior = scrollBehavior
            )
        },
        content = { padding ->
            NewTeamDetailCard(
                team = teamDetail,
                roster= roster,
                modifier = Modifier.padding(padding),
                schedule = teamSchedule,
                stats = stats
            )

        }
    )






}



@Composable
fun PastGames(
    schedule: ScheduleDomainModel,
    teamColor: Color = Color.Gray,
    altColor: Color = Color.DarkGray, // passed through from team, kept for future use
) {
    val sport = extractSportFromLogoUrl(schedule.team.logo)

    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Column {
            // ── HEADER ──────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .width(3.dp)
                            .height(16.dp)
                            .background(teamColor)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "RESULTS",
                        style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 2.sp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (schedule.team.recordSummary.isNotBlank()) {
                        Text(
                            text = schedule.team.recordSummary,
                            style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 0.5.sp),
                            fontWeight = FontWeight.Bold,
                            color = teamColor
                        )
                    }
                    Text(
                        text = schedule.season.name.uppercase(),
                        style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            HorizontalDivider(thickness = 1.dp, color = teamColor.copy(alpha = 0.18f))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp)
            ) {
                items(schedule.events) { event ->
                    PastEventCard(event = event, teamColor = teamColor, sport = sport)
                }
            }
        }
    }
}


@Composable
fun PastEventCard(
    event: ScheduleEventModel,
    teamColor: Color = Color.Gray,
    sport: String = "nfl",
) {
    val competition = event.competitions.firstOrNull() ?: return
    val home = competition.competitors.find { it.homeAway == "home" }
    val away = competition.competitors.find { it.homeAway == "away" }
    val status = competition.status
    val isCompleted = status.type.completed
    val athletes = status.featuredAthletes.orEmpty()

    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.width(272.dp)
    ) {
        Column {
            // ── GAME HEADER ──────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(teamColor.copy(alpha = 0.07f))
                    .padding(horizontal = 12.dp, vertical = 9.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = event.shortName,
                        style = MaterialTheme.typography.labelMedium.copy(letterSpacing = 1.sp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = event.date.toDate()?.formatTo("EEE, MMM dd") ?: "",
                        style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 0.5.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                val badgeColor = when {
                    isCompleted -> teamColor
                    else -> Color(0xFFF57C00)
                }
                Surface(color = badgeColor, shape = RoundedCornerShape(3.dp)) {
                    Text(
                        text = status.type.shortDetail.uppercase(),
                        style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.sp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                    )
                }
            }

            HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant)

            // ── TEAMS & SCORES ───────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TeamScoreColumn(competitor = away, isCompleted = isCompleted, teamColor = teamColor)

                Text(
                    text = "@",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                    fontWeight = FontWeight.Light
                )

                TeamScoreColumn(competitor = home, isCompleted = isCompleted, teamColor = teamColor)
            }

            // ── FEATURED ATHLETES ────────────────────────────────────
            if (athletes.isNotEmpty()) {
                HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant)
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    athletes.forEach { featuredAthlete ->
                        GameAthleteRow(
                            featuredAthlete = featuredAthlete,
                            sport = sport,
                            teamColor = teamColor
                        )
                    }
                }
            }

            // ── VENUE + ATTENDANCE ───────────────────────────────────
            HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(teamColor.copy(alpha = 0.03f))
                    .padding(horizontal = 12.dp, vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = teamColor.copy(alpha = 0.7f),
                        modifier = Modifier.size(11.dp)
                    )
                    Text(
                        text = competition.venue.fullName,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }
                if (competition.attendance > 0) {
                    Text(
                        text = "%,d".format(competition.attendance),
                        style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 0.3.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}


@Composable
private fun TeamScoreColumn(
    competitor: ScheduleCompetitorModel?,
    isCompleted: Boolean,
    teamColor: Color,
) {
    if (competitor == null) return
    val scoreText = competitor.score?.displayValue
        ?.takeIf { it.isNotBlank() }
        ?: competitor.score?.value?.takeIf { it > 0f }?.toInt()?.toString()
        ?: if (isCompleted) "0" else "—"
    val isWinner = competitor.winner == true

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(competitor.team.logos)
                .crossfade(true)
                .build(),
            contentDescription = competitor.team.displayName,
            modifier = Modifier.size(36.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = competitor.team.abbreviation,
            style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.sp),
            fontWeight = FontWeight.Bold,
            color = if (isWinner) teamColor else MaterialTheme.colorScheme.onSurfaceVariant
        )
        if (isCompleted) {
            Text(
                text = scoreText,
                style = MaterialTheme.typography.titleLarge.copy(
                    letterSpacing = (-0.5).sp,
                    fontWeight = FontWeight.Black
                ),
                color = if (isWinner) teamColor else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}


@Composable
private fun GameAthleteRow(
    featuredAthlete: ScheduleFeaturedAthleteModel,
    sport: String,
    teamColor: Color,
) {
    val headshotUrl =
        "https://a.espncdn.com/i/headshots/$sport/players/full/${featuredAthlete.playerId}.png"

    val (badgeColor, badgeText) = when (featuredAthlete.abbreviation) {
        "WP" -> Color(0xFF2E7D32) to "W"
        "LP" -> Color(0xFFD32F2F) to "L"
        "S"  -> teamColor to "SV"
        else -> teamColor to featuredAthlete.abbreviation
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Athlete headshot
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(headshotUrl)
                .crossfade(true)
                .build(),
            contentDescription = featuredAthlete.athlete.displayName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(34.dp)
                .border(1.dp, teamColor.copy(alpha = 0.35f), CircleShape)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
        )
        Spacer(modifier = Modifier.width(9.dp))

        // Name + role label + record
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = featuredAthlete.athlete.shortName,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )
            Text(
                text = featuredAthlete.displayName.uppercase(),
                style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 0.8.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1
            )
        }

        // Record + W/L/SV badge
        Column(horizontalAlignment = Alignment.End) {
            Surface(
                color = badgeColor,
                shape = RoundedCornerShape(3.dp)
            ) {
                Text(
                    text = badgeText,
                    style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 0.5.sp),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
            if (featuredAthlete.athlete.record.isNotBlank()) {
                Text(
                    text = featuredAthlete.athlete.record,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

private fun extractSportFromLogoUrl(logoUrl: String): String =
    Regex("teamlogos/([^/]+)/").find(logoUrl)?.groupValues?.getOrNull(1) ?: "nfl"


@Composable
fun TeamRecord(
    record: RecordModel,
    modifier: Modifier,
    teamColor: Color = Color.Gray,
) {
    val items = record.recordItems.filterNotNull()
    if (items.isEmpty()) return

    val primary = items[0]
    val secondaries = items.drop(1)

    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column {

            // ── HEADER ─────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .width(3.dp)
                            .height(16.dp)
                            .background(teamColor)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "TEAM RECORD",
                        style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 2.sp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Text(
                    text = primary.type.uppercase(),
                    style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.sp),
                    fontWeight = FontWeight.SemiBold,
                    color = teamColor
                )
            }

            HorizontalDivider(thickness = 1.dp, color = teamColor.copy(alpha = 0.18f))

            // ── HERO SUMMARY ────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(teamColor.copy(alpha = 0.04f))
                    .padding(start = 16.dp, end = 16.dp, top = 18.dp, bottom = 16.dp)
            ) {
                Column {
                    Text(
                        text = primary.summary,
                        style = MaterialTheme.typography.displayMedium.copy(
                            letterSpacing = (-1.5).sp,
                            fontWeight = FontWeight.Black
                        ),
                        color = teamColor
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "OVERALL RECORD",
                        style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 2.sp),
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant)

            // ── STATS GRID ──────────────────────────────────────────
            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
            ) {
                itemsIndexed(primary.stats) { index, stat ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (index > 0) {
                            Box(
                                modifier = Modifier
                                    .width(0.5.dp)
                                    .height(40.dp)
                                    .background(MaterialTheme.colorScheme.outlineVariant)
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(horizontal = 14.dp)
                        ) {
                            val displayValue = formatStatValue(stat.value)
                            Text(
                                text = displayValue,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    letterSpacing = (-0.5).sp,
                                    fontWeight = FontWeight.ExtraBold
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = stat.name.uppercase(),
                                style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.sp),
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // ── SECONDARY RECORDS (Home / Away / Conf / Div) ────────
            if (secondaries.isNotEmpty()) {
                HorizontalDivider(thickness = 1.dp, color = teamColor.copy(alpha = 0.18f))

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 12.dp),
                ) {
                    itemsIndexed(secondaries) { index, item ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (index > 0) {
                                Box(
                                    modifier = Modifier
                                        .width(0.5.dp)
                                        .height(34.dp)
                                        .background(MaterialTheme.colorScheme.outlineVariant)
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            ) {
                                Text(
                                    text = item.summary,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        letterSpacing = (-0.5).sp,
                                        fontWeight = FontWeight.ExtraBold
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = item.type.uppercase(),
                                    style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.5.sp),
                                    fontWeight = FontWeight.Medium,
                                    color = teamColor
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun formatStatValue(value: Float): String = when {
    value % 1 == 0f -> value.toInt().toString()
    value in 0f..1f -> "%.3f".format(value).trimStart('0').ifEmpty { "0" }
    else -> "%.1f".format(value)
}