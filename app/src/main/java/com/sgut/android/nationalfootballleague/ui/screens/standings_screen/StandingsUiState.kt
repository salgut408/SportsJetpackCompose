package com.sgut.android.nationalfootballleague.ui.screens.standings_screen

import com.sgut.android.nationalfootballleague.domain.domainmodels.standings_models.StandingsModel

sealed interface StandingsUiState {
    data object Loading : StandingsUiState
    data class Content(val standings: StandingsModel) : StandingsUiState
    data class Error(val message: String) : StandingsUiState
}