package com.sgut.android.nationalfootballleague.ui.screens.homelistscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.FullTeamsListsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.SportModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.TeamModel
import com.sgut.android.nationalfootballleague.domain.repositories.TeamsListsRepository
import com.sgut.android.nationalfootballleague.domain.use_cases.GetArticlesUseCase
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.BASEBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.FOOTBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NFL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.WBC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeListViewModel @Inject constructor(
    private val fullTeamsListRepository: TeamsListsRepository,
    private val getArticles: GetArticlesUseCase,
) : ViewModel() {

    private val _listUiState = MutableStateFlow(ListUiState())
    val listUiState: StateFlow<ListUiState> = _listUiState.asStateFlow()


    init {
//        to start
        loadAllNflTeams()
    }

    fun addTeamsToDb(teams: List<TeamModel>, sport: String, league: String, leagueAbrv: String) =
        viewModelScope.launch {
            fullTeamsListRepository.storeTeamsInSportsDatabaseFullInfoTable(
                teams,
                sport,
                league,
                leagueAbrv
            )
        }


    //            Default list Ui State set here
    fun loadAllNflTeams() = viewModelScope.launch {
        try {
            val fullTeamsList =
                fullTeamsListRepository.getFullSportLeagueAndTeamsList(FOOTBALL, NFL)
            val news = getArticles(FOOTBALL, NFL)
            addTeamsToDb(
                teams = fullTeamsList.sport.league?.teams ?: listOf(),
                sport = fullTeamsList.sport.name,
                league = fullTeamsList.sport.league?.name ?: "",
                leagueAbrv = fullTeamsList.sport.league?.abbreviation ?: ""
            )
            setListUiState(
                currentTeams = fullTeamsList.sport.league?.teams ?: listOf(),
                currentSport = fullTeamsList.sport.name,
                currentLeague = fullTeamsList.sport.league?.abbreviation ?: "",
                fullTeamInfo = fullTeamsList,
                sportModel = fullTeamsList.sport,
                news = news
            )
        }
        catch (e: Exception) {
            Log.e("HOME_VM", e.message.toString())
        }
    }


    fun setDifferentTeams(sport: String, league: String) = viewModelScope.launch {
        val fullTeamsList = fullTeamsListRepository.getFullSportLeagueAndTeamsList(sport, league)
        val news = getArticles(sport, league)
        addTeamsToDb(
            teams = fullTeamsList.sport.league?.teams ?: listOf(),
            sport = fullTeamsList.sport.name,
            league = fullTeamsList.sport.league?.name ?: "",
            leagueAbrv = fullTeamsList.sport.league?.abbreviation ?: ""
        )
        setListUiState(
            currentTeams = fullTeamsList.sport.league?.teams ?: listOf(),
            currentSport = fullTeamsList.sport.name,
            currentLeague = fullTeamsList.sport.league?.slug ?: "",
            fullTeamInfo = fullTeamsList,
            sportModel = fullTeamsList.sport,
            news = news
        )
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





    fun setWbc() = viewModelScope.launch {
        val fullTeamList = fullTeamsListRepository.getFullSportLeagueAndTeamsList(BASEBALL, WBC)
        val news = getArticles(BASEBALL, WBC)

        setListUiState(
            currentTeams = fullTeamList.sport.league?.teams ?: listOf(),
            currentSport = fullTeamList.sport.slug,
            currentLeague = fullTeamList.sport.league?.slug ?: "",
            fullTeamInfo = fullTeamList,
            news = news,
            sportModel = fullTeamList.sport
        )

    }
}