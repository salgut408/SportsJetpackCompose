package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsAthleteDetailsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsModel

data class GameDetailsScreenUiState(
    val currentSport: String = "",
    val currentLeague: String = "",
    val currentGameUiState: GameDetailsModel? = null,
    val curentMap: Map<String, GameDetailsAthleteDetailsModel>? = null,
    val loading: Boolean = true
    )
