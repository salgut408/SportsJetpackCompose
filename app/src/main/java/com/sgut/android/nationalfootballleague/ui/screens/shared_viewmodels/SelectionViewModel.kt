package com.sgut.android.nationalfootballleague.ui.screens.shared_viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.SportModel
import com.sgut.android.nationalfootballleague.domain.repositories.TeamsListsRepository
import com.sgut.android.nationalfootballleague.domain.use_cases.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SelectionViewModel @Inject constructor(
    private val fullTeamsListRepository: TeamsListsRepository,
    private val getArticles: GetArticlesUseCase,
): ViewModel() {
    private val _selectionFullSportUiState = MutableStateFlow(SportModel())
    val selectionUiFullSportState: StateFlow<SportModel> = _selectionFullSportUiState.asStateFlow()

    private val _articleList = MutableStateFlow(ArticlesListModel())
    val articleList: StateFlow<ArticlesListModel> = _articleList.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

     private fun loadSport(sport: String, league: String) {
         viewModelScope.launch {

             val sportModel =  fullTeamsListRepository.getSport(sport, league)
             loadNews(sport, league)
             Timber.d("SAL_GUT sportModel  $sportModel")

             _selectionFullSportUiState.update {
                 it.copy(
                     id = sportModel.id,
                     uid = sportModel.uid,
                     name = sportModel.name,
                     slug = sportModel.slug,
                     league = sportModel.league
                 )
             }
             Timber.d("SAL_GUT SPORT SELECTED: ${selectionUiFullSportState.value}")
         }
    }

    fun setDifferentSport(sport: String, league: String) = viewModelScope.launch {
        loadSport(sport, league)
        loadNews(sport, league)
    }

    private suspend fun loadNews(sport: String, league: String) {
       _articleList.value = getArticles(sport, league)
    }
}