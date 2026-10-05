package com.sgut.android.nationalfootballleague.ui.screens.scoreboardscreen

import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.ScoreboardData
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.BasicScoreboardModel

sealed interface ScoreboardUiState {
    data object Loading : ScoreboardUiState
    data class Content(
        val sport: String,
        val league: String,
        val defaultScoreboard: BasicScoreboardModel,
        val abstractScoreData: ScoreboardData?,
        val articles: ArticlesListModel,
    ) : ScoreboardUiState
    data class Error(val message: String) : ScoreboardUiState
}