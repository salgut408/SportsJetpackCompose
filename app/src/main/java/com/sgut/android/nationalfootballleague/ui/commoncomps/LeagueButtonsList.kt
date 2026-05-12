package com.sgut.android.nationalfootballleague.ui.commoncomps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.sgut.android.nationalfootballleague.ui.screens.homelistscreen.LabelText


@Composable
fun LeagueSelectionRow(
    leagues: List<Pair<Pair<String, String>, Int>>,
    onLeagueSelected: (sport: String, league: String) -> Unit,
    padding: PaddingValues
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = padding,
    ) {
        items(leagues) { league ->
            OutlinedButton(onClick = {
                val (sport, leagueName) = league.first
                onLeagueSelected(sport, leagueName)
            }) {
                LabelText(stringResId = league.second)
            }
        }
    }
}