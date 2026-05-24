package com.sgut.android.nationalfootballleague.ui.screens.teamdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.AthletesRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_stats_models.TeamStatsModel
import com.sgut.android.nationalfootballleague.ui.commoncomps.NormalDivider
import com.sgut.android.nationalfootballleague.ui.commoncomps.DefaultCard


@Composable
fun TabLayout(
    modifier: Modifier,
    people: List<AthletesRosterModel>,
    team: FullTeamDetailWithRosterModel,
    stats: TeamStatsModel,
) {
    val teamColor = HexToJetpackColor2.getColor(team.color)
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Stats", "Roster")

    DefaultCard(modifier = modifier) {
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = teamColor.copy(alpha = 0.09f),
            contentColor = teamColor,
            indicator = { tabPositions ->
                if (tabIndex < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                        color = teamColor,
                    )
                }
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    selectedContentColor = teamColor,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (tabIndex == index) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                )
            }
        }
        when (tabIndex) {
            0 -> TeamStats(team = team, modifier = modifier, stats = stats)
            1 -> PeopleList(list = people, modifier = modifier, team = team)
        }
    }
}


@Composable
fun TeamStats(
    modifier: Modifier,
    team: FullTeamDetailWithRosterModel,
    stats: TeamStatsModel,
) {
    val teamColor = HexToJetpackColor2.getColor(team.color)
    val altColor = HexToJetpackColor2.getColor(team.alternateColor)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Section accent header
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(20.dp)
                    .background(teamColor, RoundedCornerShape(2.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Season Overview",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Surface(
            color = teamColor.copy(alpha = 0.08f),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = team.displayName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (team.standingSummary.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = team.standingSummary,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Surface(
                    color = teamColor,
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = stats.team.standingSummary,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun InjuriesCard(
    team: FullTeamDetailWithRosterModel,
    modifier: Modifier,
) {
    val teamColor = HexToJetpackColor2.getColor(team.color)
    val altColor = HexToJetpackColor2.getColor(team.alternateColor)
    val injuries = team.athletes.filter { it.injuries?.isNotEmpty() == true }

    if (injuries.isEmpty()) return

    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        // Team-gradient header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.horizontalGradient(listOf(teamColor, altColor)))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Injury Report",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Surface(
                color = Color.White.copy(alpha = 0.22f),
                shape = RoundedCornerShape(20.dp),
            ) {
                Text(
                    text = "${injuries.size} players",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp)
                )
            }
        }

        InjuredPlayerColumn(injuredList = injuries, teamColor = teamColor)
    }
}


@Composable
fun InjuredPlayerColumn(
    injuredList: List<AthletesRosterModel>,
    teamColor: Color,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        injuredList.forEachIndexed { index, athlete ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        if (index % 2 == 0) teamColor.copy(alpha = 0.04f)
                        else Color.Transparent
                    )
                    .drawBehind {
                        drawRect(
                            color = teamColor.copy(alpha = 0.75f),
                            topLeft = Offset(0f, 0f),
                            size = Size(4.dp.toPx(), size.height)
                        )
                    }
                    .padding(start = 14.dp, end = 12.dp, top = 10.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(athlete.headshot.href)
                            .crossfade(true)
                            .build(),
                        contentDescription = athlete.displayName,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(46.dp)
                            .border(2.dp, teamColor.copy(alpha = 0.65f), CircleShape)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = athlete.displayName,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(
                                text = athlete.position.abbreviation,
                                style = MaterialTheme.typography.labelSmall,
                                color = teamColor,
                                fontWeight = FontWeight.SemiBold
                            )
                            if (athlete.jersey.isNotBlank()) {
                                Text(
                                    text = "· #${athlete.jersey}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                athlete.injuries?.firstOrNull()?.let { injury ->
                    if (!injury.injuryStatus.isNullOrBlank()) {
                        Surface(
                            color = Color(0xFFD32F2F).copy(alpha = 0.12f),
                            shape = RoundedCornerShape(6.dp),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp, Color(0xFFD32F2F).copy(alpha = 0.5f)
                            )
                        ) {
                            Text(
                                text = injury.injuryStatus,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFD32F2F),
                                modifier = Modifier.padding(horizontal = 7.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
            if (index < injuredList.lastIndex) {
                HorizontalDivider(
                    thickness = 0.5.dp,
                    color = teamColor.copy(alpha = 0.15f),
                    modifier = Modifier.padding(start = 14.dp)
                )
            }
        }
    }
}


@Composable
fun PeopleList(
    list: List<AthletesRosterModel>,
    modifier: Modifier,
    team: FullTeamDetailWithRosterModel,
) {
    val teamColor = HexToJetpackColor2.getColor(team.color)
    val filtered = list.filter { it.headshot.href?.isNotEmpty() == true }
    val chunked = filtered.chunked(2)

    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(18.dp)
                    .background(teamColor, shape = RoundedCornerShape(2.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Roster  ·  ${filtered.size} players",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        HorizontalDivider(thickness = 0.5.dp, color = teamColor.copy(alpha = 0.25f))

        chunked.forEach { pair ->
            Row(modifier = Modifier.fillMaxWidth()) {
                pair.forEach { athlete ->
                    VerticalAthleteCard(
                        athelete = athlete,
                        team = team,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (pair.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}


@Composable
fun NameAndPosition(athlete: AthletesRosterModel) {
    Row {
        Text(text = athlete.displayName, style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = athlete.position.abbreviation,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}