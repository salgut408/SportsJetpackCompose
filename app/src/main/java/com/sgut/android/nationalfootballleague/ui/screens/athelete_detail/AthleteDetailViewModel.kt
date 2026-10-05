package com.sgut.android.nationalfootballleague.ui.screens.athelete_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.nationalfootballleague.domain.domainmodels.full_athlete.FullAthleteModel
import com.sgut.android.nationalfootballleague.domain.repositories.AthleteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject

/**
 * Owns the Athlete Details state. Internal source flows are combined into a
 * single sealed [AthleteDetailScreenUiState], same pattern as the Game/Team
 * detail ViewModels.
 */
@HiltViewModel
class AthleteDetailViewModel @Inject constructor(
    private val athleteRepository: AthleteRepository,
) : ViewModel() {

    private val _sport = MutableStateFlow("")
    private val _league = MutableStateFlow("")
    private val _athlete = MutableStateFlow<FullAthleteModel?>(null)
    private val _errorMessage = MutableStateFlow<String?>(null)

    val uiState: StateFlow<AthleteDetailScreenUiState> = combine(
        _sport,
        _league,
        _athlete,
        _errorMessage,
    ) { sport, league, athlete, errorMessage ->
        when {
            errorMessage != null -> AthleteDetailScreenUiState.Error(errorMessage)
            athlete == null -> AthleteDetailScreenUiState.Loading
            else -> AthleteDetailScreenUiState.Content(
                sport = sport,
                league = league,
                athlete = athlete,
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AthleteDetailScreenUiState.Loading,
    )

    fun loadAthlete(sport: String, league: String, athleteId: String) {
        viewModelScope.launch {
            _errorMessage.value = null
            try {
                val athlete = athleteRepository.getAthlete(
                    sport.lowercase(Locale.ROOT),
                    league.lowercase(Locale.ROOT),
                    athleteId,
                )
                _sport.value = sport
                _league.value = league
                _athlete.value = athlete
            } catch (e: Exception) {
                Timber.e(e, "loadAthlete failed for $sport/$league/$athleteId")
                _errorMessage.value = "Couldn't load athlete details. Please try again."
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
