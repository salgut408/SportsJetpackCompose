package com.sgut.android.nationalfootballleague.ui.screens.scoreboardscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.ScoreboardData
import com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard.BaseballScoreBoardNetwork
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.BasicScoreboardModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.TennisScoreboardModel
import com.sgut.android.nationalfootballleague.domain.repositories.ScoreboardRepository
import com.sgut.android.nationalfootballleague.domain.use_cases.GetArticlesUseCase
import com.sgut.android.nationalfootballleague.domain.use_cases.GetBaseballSituationUseCase
import com.sgut.android.nationalfootballleague.domain.use_cases.GetScoresUseCase
import com.sgut.android.nationalfootballleague.domain.use_cases.AbstractScoresUseCase
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.ATP
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.TENNIS
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
    private val scoreboardRepository: ScoreboardRepository,
    private val getArticles: GetArticlesUseCase,
    private val getScores: GetScoresUseCase,
    private val newScoressCase: AbstractScoresUseCase,
    private val getBaseballSituationUseCase: GetBaseballSituationUseCase
) : ViewModel() {

    private val _scoreboardUiState = MutableStateFlow(ScoreboardUiState())

    var scoreboardModelState: StateFlow<ScoreboardUiState> = _scoreboardUiState.asStateFlow()

    private val _abstractScoreboard = MutableLiveData<ScoreboardData>()
    val abstractScoreboard: LiveData<ScoreboardData> get() = _abstractScoreboard

    private val _baseballScoreboard = MutableStateFlow(BaseballScoreBoardNetwork())
    var baseballScoreboard: StateFlow<BaseballScoreBoardNetwork> = _baseballScoreboard.asStateFlow()

    private var _tennis = MutableStateFlow(TennisScoreboardModel())
    var tennis: StateFlow<TennisScoreboardModel> =_tennis


    init {

    }

//    fun loadBaseballScoreboard(sport: String, league: String) = viewModelScope.launch {
//        val baseballScoreboard = scoreboardRepository.getBaseballScoreboard(sport, league )
//        _baseballScoreboard.emit(baseballScoreboard).printToLog("BBAL")
//    }

    fun loadGenericScoreboard(sport: String, league: String) = viewModelScope.launch {
        try {
            fetchAbstractScoreboard(sport, league)
//            _tennis.emit(scoreboardRepository.getTennisScoreBoard(TENNIS, ATP))
//            _tennis.emit(scoreboardRepository.getTennisScoreBoard(TENNIS, ATP)) // this is just chekcing

            val news = getArticles(sport, league)
            val currentScoreboardModelUiState = getScores(sport, league)

            val newAbstractScores = newScoressCase(sport, league)

//            Timber.d("SAL_GUT newAbstractScores in vm ${_abstractScoreboard.value}")

            setScoreboardUiState(
                    sport, league,
                    currentScoreboardModelUiState,
                    news,
                    newAbstractScores
                )
//            Timber.d("SAL_GUT WHOLE SCOREBOARD UI STATE : ${_scoreboardUiState.value.abstractScoreData}")
        } catch (e: Exception) {
            Timber.e("ERROR loadGenericScoreboard")
        }
    }


    fun fetchAbstractScoreboard(sport: String, league: String) {
        viewModelScope.launch {
            val scores = scoreboardRepository.getAbstractScoreBoard(sport, league)
            _abstractScoreboard.postValue(scores)
//            Timber.d("SAL_GUT VIEWMODEL ABSTRACT SCOREBOARD: $scores")
            val news = getArticles(sport, league)
            val currentScoreboardModelUiState = getScores(sport, league)
            val newAbstractScores = newScoressCase(sport, league)
            setScoreboardUiState(
                sport,
                league,
                currentScoreboardModelUiState,
                news,
                newAbstractScores
                )
        }
    }

    private fun setScoreboardUiState(
        currentSport: String,
        currentLeague: String,
        currentDefaultScoreboardModelUiState: BasicScoreboardModel,
        currentNews: ArticlesListModel,
        abstractScoreData: ScoreboardData?,
    ) {
        _scoreboardUiState.update {
            it.copy(
                currentSport = currentSport,
                currentLeague = currentLeague,
                defaultScoreboardModelUiState = currentDefaultScoreboardModelUiState,
                currentArticles = currentNews,
                abstractScoreData = abstractScoreData
            )
        }
    }

}