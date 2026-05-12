package com.sgut.android.nationalfootballleague.ui.screens.homelistscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.FullTeamsListsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.SportModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.TeamModel
import com.sgut.android.nationalfootballleague.domain.repositories.TeamsListsRepository
import com.sgut.android.nationalfootballleague.domain.use_cases.GetArticlesUseCase
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.FOOTBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NFL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeListViewModel @Inject constructor(
    private val fullTeamsListRepository: TeamsListsRepository,
    private val getArticles: GetArticlesUseCase,
) : ViewModel() {

    private val _listUiState = MutableStateFlow(ListUiState())
    val listUiState: StateFlow<ListUiState> = _listUiState.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage


    private fun addTeamsToDb(teams: List<TeamModel>, sport: String, league: String, leagueAbrv: String) =
        viewModelScope.launch {
            fullTeamsListRepository.storeTeamsInSportsDatabaseFullInfoTable(
                teams,
                sport,
                league,
                leagueAbrv
            )
        }


    private suspend fun loadTeamsAndSetUiState(sport: String, league: String) {
        try {
            val fullTeamsList = fullTeamsListRepository.getFullSportLeagueAndTeamsList(sport, league)
            val news = getArticles(sport, league)


            setListUiState(
                currentTeams = fullTeamsList.sport.league?.teams ?: listOf(),
                currentSport = fullTeamsList.sport.name,
                currentLeague = fullTeamsList.sport.league?.abbreviation ?: "",
                fullTeamInfo = fullTeamsList,
                sportModel = fullTeamsList.sport,
                news = news
            )
        } catch (e: Exception) {
            Timber.e(e, "An error occurred while loading teams for sport: $sport, league: $league")
            _errorMessage.emit("An error happened loading teams: ${e.message}")
        }
    }



    private fun setListUiState(
        currentSport: String,
        currentLeague: String,
        currentTeams: List<TeamModel>,
        fullTeamInfo: FullTeamsListsModel,
        sportModel: SportModel,
        news: ArticlesListModel,
    ) {
        _listUiState.update {
            it.copy(
                currentSport = currentSport,
                currentLeague = currentLeague,
                currentTeams = currentTeams,
                fullTeamInfo = fullTeamInfo,
                sportModel = sportModel,
                currentNews = news
            )
        }
    }
}