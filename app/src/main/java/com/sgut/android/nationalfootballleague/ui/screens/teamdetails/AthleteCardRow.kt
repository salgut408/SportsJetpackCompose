package com.sgut.android.nationalfootballleague.ui.screens.teamdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailWithRosterModel

@Composable
fun AtheleteRow(
    team: FullTeamDetailWithRosterModel
) {
    val athletesList =team.athletes

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {

        items(athletesList){ athlete ->
            VerticalAthleteCard(athelete = athlete, team = team)
        }
    }
}


@Composable
fun InjuredAtheleteRow(
    team: FullTeamDetailWithRosterModel
) {
    val athletesList =team.athletes
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(
            16.dp
        )
    ) {
        items(athletesList){ athlete ->
            if (athlete.injuries!!.isNotEmpty()) {
                AltheleteCard2(athelete = athlete, modifier = Modifier.padding(5.dp))
            }
        }
    }
}