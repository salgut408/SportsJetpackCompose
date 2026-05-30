package com.sgut.android.nationalfootballleague.ui.screens.athelete_detail

import androidx.compose.runtime.Immutable
import com.sgut.android.nationalfootballleague.domain.domainmodels.full_athlete.FullAthleteModel

/**
 * Sealed state for the Athlete Details screen — same Loading/Content/Error
 * shape as the other detail screens so the Route/Screen/Content layering is
 * consistent across the app.
 */
sealed interface AthleteDetailScreenUiState {

    data object Loading : AthleteDetailScreenUiState

    @Immutable
    data class Content(
        val sport: String,
        val league: String,
        val athlete: FullAthleteModel,
    ) : AthleteDetailScreenUiState

    data class Error(val message: String) : AthleteDetailScreenUiState
}
