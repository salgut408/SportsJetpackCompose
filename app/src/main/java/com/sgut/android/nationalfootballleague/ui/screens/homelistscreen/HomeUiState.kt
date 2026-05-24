package com.sgut.android.nationalfootballleague.ui.screens.homelistscreen

import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.SportModel

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Content(
        val sport: SportModel,
        val articles: ArticlesListModel,
    ) : HomeUiState
}