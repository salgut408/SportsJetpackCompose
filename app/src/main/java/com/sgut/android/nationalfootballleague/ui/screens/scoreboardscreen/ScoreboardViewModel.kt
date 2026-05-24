package com.sgut.android.nationalfootballleague.ui.screens.scoreboardscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.ScoreboardData
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.BasicScoreboardModel
import com.sgut.android.nationalfootballleague.domain.use_cases.AbstractScoresUseCase
import com.sgut.android.nationalfootballleague.domain.use_cases.GetArticlesUseCase
import com.sgut.android.nationalfootballleague.domain.use_cases.GetScoresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Two scoreboard parses are kept in parallel intentionally:
 *  - [getScores] populates `defaultScoreboard` — the source of truth for everything
 *    the screen currently renders (events, scores, R/H/E, situation, linescores).
 *  - [getAbstractScores] populates `abstractScoreData` via the polymorphic deserializer
 *    that handles sport-specific variants (BaseballCompetition vs TennisCompetition vs
 *    SoccerCompetition etc.). Today it's used narrowly for the tennis-fallback
 *    rendering and as a preferred logo source, but it's the architecturally correct
 *    shape for a multi-sport scoreboard and is kept warm for upcoming consumers
 *    (the "today's games" hero card on Home, future per-sport rendering).
 *
 * Cost: one extra HTTP call per load (same `/scoreboard` URL, different parse).
 * Resolution: when the hero card or another consumer needs polymorphic data, migrate
 * the screen to read everything from `abstractScoreData` and retire `defaultScoreboard`.
 */
@HiltViewModel
class ScoreboardViewModel @Inject constructor(
    private val getArticles: GetArticlesUseCase,
    private val getScores: GetScoresUseCase,
    private val getAbstractScores: AbstractScoresUseCase,
) : ViewModel() {

    // Pull-to-refresh state — stays outside the sealed UI state so refresh keeps content visible.
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    // Internal source flows — each represents one slice of the screen's state.
    private val _sport = MutableStateFlow("")
    private val _league = MutableStateFlow("")
    private val _defaultScoreboard = MutableStateFlow(BasicScoreboardModel())
    private val _abstractScoreData = MutableStateFlow<ScoreboardData?>(null)
    private val _articles = MutableStateFlow(ArticlesListModel())
    private val _isLoading = MutableStateFlow(false)
    private val _errorMessage = MutableStateFlow<String?>(null)

    // Combine all the rendering inputs into one sealed UI state.
    // `combine` takes up to 5 flows directly; we have 6, so use the vararg form.
    val uiState: StateFlow<ScoreboardUiState> = combine(
        listOf(_sport, _league, _defaultScoreboard, _abstractScoreData, _articles, _isLoading, _errorMessage)
    ) { values ->
        @Suppress("UNCHECKED_CAST")
        val sport = values[0] as String
        @Suppress("UNCHECKED_CAST")
        val league = values[1] as String
        @Suppress("UNCHECKED_CAST")
        val defaultScoreboard = values[2] as BasicScoreboardModel
        @Suppress("UNCHECKED_CAST")
        val abstractScoreData = values[3] as ScoreboardData?
        @Suppress("UNCHECKED_CAST")
        val articles = values[4] as ArticlesListModel
        @Suppress("UNCHECKED_CAST")
        val isLoading = values[5] as Boolean
        @Suppress("UNCHECKED_CAST")
        val errorMessage = values[6] as String?

        when {
            errorMessage != null -> ScoreboardUiState.Error(errorMessage)
            isLoading -> ScoreboardUiState.Loading
            else -> ScoreboardUiState.Content(
                sport = sport,
                league = league,
                defaultScoreboard = defaultScoreboard,
                abstractScoreData = abstractScoreData,
                articles = articles,
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ScoreboardUiState.Loading,
    )

    fun loadScoreboard(sport: String, league: String, isRefresh: Boolean = false) {
        viewModelScope.launch {
            if (isRefresh) _isRefreshing.value = true else _isLoading.value = true
            _errorMessage.value = null
            try {
                coroutineScope {
                    val articlesDeferred = async { getArticles(sport, league) }
                    val scoresDeferred = async { getScores(sport, league) }
                    val abstractDeferred = async { getAbstractScores(sport, league) }

                    _articles.value = articlesDeferred.await()
                    _defaultScoreboard.value = scoresDeferred.await()
                    _abstractScoreData.value = abstractDeferred.await()
                    _sport.value = sport
                    _league.value = league
                }
            } catch (e: Exception) {
                Timber.e(e, "loadScoreboard failed for $sport/$league")
                _errorMessage.value = "Couldn't load scoreboard for $league"
            } finally {
                if (isRefresh) _isRefreshing.value = false else _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}