package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsAthleteDetailsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsModel
import com.sgut.android.nationalfootballleague.domain.repositories.GameDetailsRepository
import com.sgut.android.nationalfootballleague.domain.use_cases.PlayersMapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val gameDetailsRepository: GameDetailsRepository,
    private val playersMap: PlayersMapUseCase,
) : ViewModel() {

    // Internal source flows — combined into uiState below.
    private val _sport = MutableStateFlow("")
    private val _league = MutableStateFlow("")
    private val _game = MutableStateFlow<GameDetailsModel?>(null)
    private val _players = MutableStateFlow<Map<String, GameDetailsAthleteDetailsModel>>(emptyMap())
    private val _errorMessage = MutableStateFlow<String?>(null)

    val uiState: StateFlow<GameDetailsUiState> = combine(
        _sport,
        _league,
        _game,
        _players,
        _errorMessage,
    ) { sport, league, game, players, errorMessage ->
        when {
            errorMessage != null -> GameDetailsUiState.Error(errorMessage)
            game == null -> GameDetailsUiState.Loading
            else -> GameDetailsUiState.Content(
                sport = sport,
                league = league,
                game = game,
                players = players,
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = GameDetailsUiState.Loading,
    )

    /**
     * Fast lookup helper for screen sections that need a single athlete by ID
     * (the baseball-specific situation block in particular).
     */
    fun getPlayerFromId(id: String): GameDetailsAthleteDetailsModel =
        _players.value[id] ?: GameDetailsAthleteDetailsModel()

    fun loadGameDetails(sport: String, league: String, event: String) {
        viewModelScope.launch {
            _errorMessage.value = null
            try {
                val game = gameDetailsRepository.getGameDetails(sport, league, event)
                _sport.value = sport
                _league.value = league
                _game.value = game

                val teamAbbreviations = listOf(
                    game.boxscore?.teams?.firstOrNull()?.team?.abbreviation.orEmpty(),
                    game.boxscore?.teams?.lastOrNull()?.team?.abbreviation.orEmpty(),
                ).filter { it.isNotBlank() }

                if (teamAbbreviations.isNotEmpty()) {
                    _players.value = playersMap(sport, league, teamAbbreviations)
                }
            } catch (e: Exception) {
                Timber.e(e, "loadGameDetails failed for $sport/$league/$event")
                _errorMessage.value = "Couldn't load game details. Please try again."
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}