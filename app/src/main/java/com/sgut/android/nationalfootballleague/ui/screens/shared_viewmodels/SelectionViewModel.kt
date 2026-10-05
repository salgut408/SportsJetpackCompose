package com.sgut.android.nationalfootballleague.ui.screens.shared_viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.SportModel
import com.sgut.android.nationalfootballleague.domain.repositories.SportRepository
import com.sgut.android.nationalfootballleague.domain.use_cases.GetArticlesUseCase
import com.sgut.android.nationalfootballleague.ui.screens.homelistscreen.HomeUiState
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.BASEBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.MLB
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

@HiltViewModel
class SelectionViewModel @Inject constructor(
    private val sportRepository: SportRepository,
    private val getArticles: GetArticlesUseCase,
) : ViewModel() {

    // Cross-screen selection — read by Scoreboard, Game Details, etc.
    private val _sport = MutableStateFlow(SportModel())
    val sport: StateFlow<SportModel> = _sport.asStateFlow()

    // Internal source flows — combined into homeUiState below
    private val _articles = MutableStateFlow(ArticlesListModel())
    private val _isLoading = MutableStateFlow(false)

    // The home screen's single state. Derived from _sport + _articles + _isLoading.
    val homeUiState: StateFlow<HomeUiState> = combine(
        _sport,
        _articles,
        _isLoading,
    ) { sport, articles, isLoading ->
        if (isLoading) HomeUiState.Loading
        else HomeUiState.Content(sport = sport, articles = articles)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState.Loading,
    )

    // Pull-to-refresh state — separate from initial-load so refresh keeps the content visible
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    // Wall-clock millis of the last successful load; drives "Updated Xm ago" UI
    private val _lastUpdatedMs = MutableStateFlow<Long?>(null)
    val lastUpdatedMs: StateFlow<Long?> = _lastUpdatedMs.asStateFlow()

    // One-shot error signal — drives a snackbar via LaunchedEffect on the screen
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private fun loadSport(sportSlug: String, leagueSlug: String, isRefresh: Boolean = false) {
        viewModelScope.launch {
            if (isRefresh) _isRefreshing.value = true else _isLoading.value = true
            _errorMessage.value = null
            try {
                coroutineScope {
                    val sportDeferred = async { sportRepository.getSport(sportSlug, leagueSlug) }
                    val articlesDeferred = async { getArticles(sportSlug, leagueSlug) }
                    _sport.value = sportDeferred.await()
                    _articles.value = articlesDeferred.await()
                }
                _lastUpdatedMs.value = System.currentTimeMillis()
            } catch (e: Exception) {
                Timber.e(e, "loadSport failed for $sportSlug/$leagueSlug")
                _errorMessage.value = "Failed to load $leagueSlug. Please try again."
            } finally {
                if (isRefresh) _isRefreshing.value = false else _isLoading.value = false
            }
        }
    }

    fun setDifferentSport(sport: String, league: String) {
        loadSport(sport, league)
    }

    fun refresh() {
        val current = _sport.value
        if (current.slug.isNotBlank() && current.league.slug.isNotBlank()) {
            loadSport(current.slug, current.league.slug, isRefresh = true)
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }

    fun ensureDefaultSportSelected() {
        if (_sport.value.league.slug.isBlank()) {
            loadSport(DEFAULT_SPORT, DEFAULT_LEAGUE)
        }
    }

    private companion object {
        private const val DEFAULT_SPORT = BASEBALL
        private const val DEFAULT_LEAGUE = MLB
    }
}