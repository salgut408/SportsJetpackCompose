package com.sgut.android.nationalfootballleague.ui.screens.standings_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sgut.android.nationalfootballleague.domain.domainmodels.standings_models.ChildrenModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.standings_models.StandingsResponseModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.standings_models.TeamModel
import com.sgut.android.nationalfootballleague.ui.commoncomps.CardHeaderText
import com.sgut.android.nationalfootballleague.ui.commoncomps.DefaultCard
import com.sgut.android.nationalfootballleague.ui.commoncomps.GenericImageLoader
import com.sgut.android.nationalfootballleague.ui.commoncomps.NormalDivider

@Composable
fun Standings(
    modifier: Modifier = Modifier,
    sport: String,
    league: String,
    type: String,
    standingsViewModel: StandingsViewModel = hiltViewModel(),
) {
    LaunchedEffect(sport, league, type) {
        standingsViewModel.loadStandings(sport, league, type)
    }

    val state by standingsViewModel.uiState.collectAsStateWithLifecycle()

    DefaultCard(modifier = modifier) {
        CardHeaderText(text = "Standings", emoji = "🏆")
        NormalDivider()

        when (val s = state) {
            StandingsUiState.Loading -> StandingsLoadingState()
            is StandingsUiState.Content -> StandingsList(
                children = s.standings.children,
                modifier = modifier,
            )
            is StandingsUiState.Error -> StandingsErrorState(message = s.message)
        }
    }
}

@Composable
private fun StandingsLoadingState() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun StandingsErrorState(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = message, fontSize = 12.sp)
    }
}

@Composable
private fun StandingsList(
    children: List<ChildrenModel>,
    modifier: Modifier,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
    ) {
        items(children) { child ->
            Children(child = child, modifier = modifier)
        }
    }
}

@Composable
fun Children(child: ChildrenModel, modifier: Modifier) {
    Column(horizontalAlignment = Alignment.End) {
        Text(text = child.abbreviation, fontWeight = FontWeight.Bold, fontSize = 10.sp)
        Text(text = child.standings.displayName, fontWeight = FontWeight.Bold, fontSize = 10.sp)
        DivisionStandings(standings = child.standings, modifier = modifier)
    }
}

@Composable
fun DivisionStandings(standings: StandingsResponseModel, modifier: Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            standings.entries.map { entry ->
                TeamComp(team = entry.team, modifier = modifier)
            }
        }
    }
}

@Composable
fun TeamComp(team: TeamModel, modifier: Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = team.abbreviation, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        GenericImageLoader(
            obj = team.logos.firstOrNull()?.href ?: "",
            modifier = modifier.size(30.dp),
        )
    }
}