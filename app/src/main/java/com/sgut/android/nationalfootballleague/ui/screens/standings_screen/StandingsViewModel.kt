package com.sgut.android.nationalfootballleague.ui.screens.standings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.nationalfootballleague.domain.domainmodels.standings_models.StandingsModel
import com.sgut.android.nationalfootballleague.domain.repositories.StandingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StandingsViewModel @Inject constructor(
    private val standingsRepository: StandingsRepository,
): ViewModel() {

    private val _standingsUiState = MutableStateFlow(StandingsUiState())
    var standingsState: StateFlow<StandingsUiState> = _standingsUiState.asStateFlow()


    fun loadStandings(sport: String, league: String, type: String) = viewModelScope.launch {
        try {
            val currentStandingsUiState = standingsRepository.getStandings(sport, league, type)
            setStandingsUiState(sport, league, currentStandingsUiState)
        } catch (e :Exception){
           Timber.e("loadStandings FAIL: $e")

        }

    }

    private fun setStandingsUiState(
        currentSport: String,
        currentLeague: String,
        currentStandingsModelUiState: StandingsModel,
        ) {
//        Timber.d("STANDINGS: $currentStandingsModelUiState")
        _standingsUiState.update {
            it.copy(
                currentSport = currentSport,
                currentLeague = currentLeague,
                standingsUiState = currentStandingsModelUiState
            )
        }
    }

}