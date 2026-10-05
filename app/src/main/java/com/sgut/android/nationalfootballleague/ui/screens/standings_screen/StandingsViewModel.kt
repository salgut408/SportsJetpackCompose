package com.sgut.android.nationalfootballleague.ui.screens.standings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.nationalfootballleague.domain.repositories.StandingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StandingsViewModel @Inject constructor(
    private val standingsRepository: StandingsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<StandingsUiState>(StandingsUiState.Loading)
    val uiState: StateFlow<StandingsUiState> = _uiState.asStateFlow()

    fun loadStandings(sport: String, league: String, type: String) = viewModelScope.launch {
        _uiState.value = StandingsUiState.Loading
        try {
            val standings = standingsRepository.getStandings(sport, league, type)
            _uiState.value = StandingsUiState.Content(standings)
        } catch (e: Exception) {
            Timber.e(e, "loadStandings failed for $sport/$league/$type")
            _uiState.value = StandingsUiState.Error("Couldn't load standings for $league")
        }
    }
}