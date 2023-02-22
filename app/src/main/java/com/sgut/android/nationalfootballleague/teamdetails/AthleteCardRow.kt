package com.sgut.android.nationalfootballleague.teamdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sgut.android.nationalfootballleague.Athletes
import com.sgut.android.nationalfootballleague.data.domainmodels.TeamDetailWithRosterModel

@Composable
fun AtheleteRow(
    team: TeamDetailWithRosterModel
) {
    val athletesList =team.athletes



    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {


        items(athletesList){ athlete ->
            if (athlete != null) {
//                AltheleteCard3(athelete = athlete, modifier = Modifier.padding(5.dp))
                VerticalAthleteCard(athelete = athlete, team = team)
            }
        }
    }
}

@Composable
fun LazyAtheleteGrid(
    team: TeamDetailWithRosterModel
) {
    val athletesList = team.athletes
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
    ){
       items(athletesList)  { athelete ->
           VerticalAthleteCard(athelete = athelete, team = team)

       }
    }
}

@Composable
fun InjuredAtheleteRow(
    team: TeamDetailWithRosterModel
) {
    val athletesList =team.athletes

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(
            16.dp)
    ) {

        items(athletesList){ athlete ->
            if (athlete.injuries!!.isNotEmpty()) {
                AltheleteCard2(athelete = athlete, modifier = Modifier.padding(5.dp))
            }
        }
    }
}