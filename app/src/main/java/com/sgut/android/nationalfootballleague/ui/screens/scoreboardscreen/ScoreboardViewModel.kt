package com.sgut.android.nationalfootballleague.ui.screens.scoreboardscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.nationalfootballleague.domain.use_cases.AbstractScoresUseCase
import com.sgut.android.nationalfootballleague.domain.use_cases.GetArticlesUseCase
import com.sgut.android.nationalfootballleague.domain.use_cases.GetScoresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ScoreboardViewModel @Inject constructor(
    private val getArticles: GetArticlesUseCase,
    private val getScores: GetScoresUseCase,
    private val getAbstractScores: AbstractScoresUseCase,
) : ViewModel() {

    private val _scoreboardUiState = MutableStateFlow(ScoreboardUiState())
    val scoreboardModelState: StateFlow<ScoreboardUiState> = _scoreboardUiState.asStateFlow()

    fun loadScoreboard(sport: String, league: String) = viewModelScope.launch {
        try {
            val news = getArticles(sport, league)
            val defaultScoreboard = getScores(sport, league)
            val abstractScores = getAbstractScores(sport, league)

            _scoreboardUiState.update {
                it.copy(
                    currentSport = sport,
                    currentLeague = league,
                    defaultScoreboardModelUiState = defaultScoreboard,
                    currentArticles = news,
                    abstractScoreData = abstractScores,
                )
            }
        } catch (e: Exception) {
            Timber.e(e, "loadScoreboard failed")
        }
    }
}