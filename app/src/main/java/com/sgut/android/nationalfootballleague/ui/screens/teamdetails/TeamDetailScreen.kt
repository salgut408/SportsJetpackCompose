package com.sgut.android.nationalfootballleague.ui.screens.teamdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sgut.android.nationalfootballleague.di.ToolBar2
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.RecordModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.*
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.GenericImageLoader
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
fun PastGames(schedule: ScheduleDomainModel) {
    Column(
        modifier = Modifier
            .background(Color.Yellow)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        PastGamesHeader(
            teamName = schedule.team.displayName,
            seasonName = schedule.season.name,
            logo = schedule.team.logo
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PastGamesBody(summary = schedule.team.recordSummary)
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(schedule.events) { event ->
                PastEventCard(event = event)
            }
        }
    }

}

@Composable
fun PastEventCard(event: ScheduleEventModel) {
    Card() {
        Text(
            text = event.shortName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        event.competitions.map { Competition(competitionModel = it) }

    }
}

@Composable
fun FeaturedAthelete(athelete: ScheduleFeaturedAthleteModel) {
    Row() {
        Text(text = athelete.displayName, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(8.dp))

        Text(text = athelete.athlete.displayName)
        Spacer(modifier = Modifier.width(8.dp))

        Text(text = athelete.team.name)
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
fun Status(status: ScheduleStatusModel) {
    Text(text = status.type.shortDetail)
}

@Composable
fun Competition(competitionModel: ScheduleCompetitionModel) {
    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = competitionModel.date.toDate()?.formatTo("MMM/dd") ?: "")
            Spacer(modifier = Modifier.width(16.dp))
            Status(status = competitionModel.status)
        }
        competitionModel.status.featuredAthletes?.map { FeaturedAthelete(athelete = it) }
    }
}


@Composable
fun PastGamesBody(summary: String) {
    Text(text = "Records Summary $summary")
}

@Composable
fun PastGamesHeader(
    teamName: String,
    seasonName: String,
    logo: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = seasonName, fontSize = 20.sp)
        GenericImageLoader(obj = logo, modifier = Modifier.size(30.dp))
    }
}

@Composable
fun TeamRecord(
    record: RecordModel,
    modifier: Modifier,
) {
    if (record.recordItems.isEmpty()) {
        Text(text = "no records")
    } else {

        val recordItems = record.recordItems.getOrNull(0)?.stats

        Card(modifier = modifier.fillMaxWidth()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondary),
            ) {
                Text(
                    text = record.recordItems.getOrNull(0)?.summary ?: "",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Text(
                    text = record.recordItems.getOrNull(0)?.type ?: "",
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.background(MaterialTheme.colorScheme.secondary)
            ) {
                Column() {
                    recordItems?.map { recordItems ->
                        Text(text = recordItems.name,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondary)
                    }
                }
                Column() {
                    recordItems?.map { recordItems ->
                        Text(text = recordItems.value.toString(),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSecondary)
                    }
                }
            }
        }
    }
}