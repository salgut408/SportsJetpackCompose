package com.sgut.android.nationalfootballleague.ui.screens.teamdetails

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.AthletesRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_stats_models.TeamStatsModel
import com.sgut.android.nationalfootballleague.ui.commoncomps.CardHeaderText
import com.sgut.android.nationalfootballleague.ui.commoncomps.NormalDivider
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.DefaultCard
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.GenericImageLoader




@Composable
fun TabLayout(
    modifier: Modifier,
    people: List<AthletesRosterModel>,
    team: FullTeamDetailWithRosterModel,
    stats: TeamStatsModel
) {
   var tabIndex by remember { mutableStateOf(0)}
    val tabTitles = listOf( "Stats", "Roster")

    DefaultCard(modifier = modifier) {
        TabRow(selectedTabIndex = tabIndex) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = tabIndex == index,
                    onClick = {tabIndex = index},
                    text = {
                        Text(text = title)
                    },
                )
            }
        }
        when(tabIndex) {
            0 -> TeamStats(team = team, modifier = modifier, stats = stats)
            1 -> PeopleList(list = people, modifier = modifier)
        }
    }

}

@Composable
fun TeamStats(
    modifier: Modifier,
    team: FullTeamDetailWithRosterModel,
    stats: TeamStatsModel
) {
    Text(text = team.displayName, fontSize = 40.sp)
    Text(text = stats.team.standingSummary, fontSize = 20.sp)
}

@Composable
fun InjuriesCard(
    team: FullTeamDetailWithRosterModel,
    modifier: Modifier,
) {

    val injuries = team.athletes.filter { it.injuries?.isNotEmpty()!! }
    DefaultCard(modifier = modifier) {
        CardHeaderText(text = "Injuries")
        NormalDivider()
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InjuredPlayerColumn(modifier = modifier, injuredList = injuries)
        }
    }
}

@Composable
fun InjuredPlayerColumn(
    modifier: Modifier,
    injuredList: List<AthletesRosterModel>,
) {

    Column() {
        injuredList.map {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                NameAndPosition(athlete = it)
                it.injuries?.map { injury ->
                    Text(text = injury.injuryStatus ?: "")
//                    Text(text = injury.shortComment ?: "")
                }
            }
        }
    }
}

@Composable
fun PeopleList(list: List<AthletesRosterModel>, modifier: Modifier) {
    DefaultCard(modifier = modifier) {
        CardHeaderText(text = "Roster")
        NormalDivider()
        RowHeader(modifier = modifier)

        // move to vm
        list.filter{it.headshot.href?.isNotEmpty() ?: false} .map { athlete ->

        AthleteRow(athlete = athlete, modifier = modifier)
            NormalDivider()
        }

    }

}

@Composable
fun AthleteRow(athlete: AthletesRosterModel, modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        GenericImageLoader(obj = athlete.headshot.href ?: "", modifier = modifier.size(60.dp))
        Spacer(modifier = modifier.width(26.dp))
        Text(text = athlete.displayName, fontSize = 15.sp)
        Spacer(modifier = modifier.width(12.dp))
        Text(text = athlete.jersey, fontSize = 10.sp)
        Text(text = athlete.position.abbreviation, fontSize = 10.sp)


    }
}

@Composable
fun RowHeader(modifier: Modifier) {
    NormalDivider()
    Row() {
        Spacer(modifier = modifier.width(60.dp))

        Text(text = "Name", fontWeight = FontWeight.Bold)
    }
    NormalDivider()


}


@Composable
fun NameAndPosition(athlete: AthletesRosterModel) {
    Row() {
        Text(text = athlete.displayName)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = athlete.position.abbreviation, color = Color.Blue)
        Spacer(modifier = Modifier.width(36.dp))


    }
}