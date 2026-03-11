package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.nationalfootballleague.domain.domainmodels.GameDetailModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsAthleteDetailsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsModel
import com.sgut.android.nationalfootballleague.domain.repositories.GameDetailsRepository
import com.sgut.android.nationalfootballleague.domain.use_cases.PlayersMapUseCase
import com.sgut.android.nationalfootballleague.ui.screens.teamdetails.HexToJetpackColor2
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val gameDetailsRepository: GameDetailsRepository,
    private val playersMap: PlayersMapUseCase,
) : ViewModel() {


//    TODO Move map from useCase to uiState

    private val _gameDetailUiState = MutableStateFlow(GameDetailsScreenUiState())
    var gameDetailUiState: StateFlow<GameDetailsScreenUiState> = _gameDetailUiState.asStateFlow()

    private val _colorsTeamList: MutableList<Color> = mutableListOf()

    private val _map = MutableStateFlow(mapOf<String, GameDetailsAthleteDetailsModel> ())
    val map: StateFlow<Map<String, GameDetailsAthleteDetailsModel>> = _map.asStateFlow()



    init {
        val evens = Array(3) {3}
        println(evens)
    }


    fun getPlayerFromId(id: String): GameDetailsAthleteDetailsModel {
        return map.value[id] ?: GameDetailsAthleteDetailsModel()
    }

    fun loadGameDetails(
        sport: String,
        league: String,
        event: String,
    ) = viewModelScope.launch {
        try {
            val newGameDeetUiState = gameDetailsRepository.getGameDetails(sport, league, event)

            setGameDetailsUiState(sport, league, newGameDeetUiState)

            val teams = listOf(
                newGameDeetUiState.boxscore?.teams?.first()?.team?.abbreviation ?: "",
                newGameDeetUiState.boxscore?.teams?.last()?.team?.abbreviation ?: "",
            )


            _map.update {
                it + playersMap(sport, league, teams)
            }

            Timber.d("SAL_GUT newGameDeetUiState: ${newGameDeetUiState.toString()}")
//            Timber.d("SAL_GUT newGameDeetUiState: ${newGameDeetUiState.}")


            newGameDeetUiState.boxscore?.teams?.forEach { i ->
                _colorsTeamList.add(HexToJetpackColor2.getColor(i.team?.color ?: "Color"))
            }
            _colorsTeamList.add(Color.Black)





        }
        catch (e: Exception) {
            Log.e("DEBUG_COLOR", e.message.toString())
        }
    }

    fun onSaveArticleClick(article: GameDetailModel) = viewModelScope.launch {
//        espnRepository.saveArticle(article)

    }

    fun returnTeamNamesForTopBar(): String {
        val first = gameDetailUiState.value.currentGameUiState?.header?.competitions?.first()?.competitors?.last()?.team?.name ?: ""
        val second = gameDetailUiState.value.currentGameUiState?.header?.competitions?.first()?.competitors?.first()?.team?.name ?: ""
        return "$first @ $second"

    }


    private fun setGameDetailsUiState(
        currentSport: String,
        currentLeague: String,
        currentGameUiState: GameDetailsModel,

    ) {
        _gameDetailUiState.update {
            it.copy(
                currentLeague = currentLeague,
                currentSport = currentSport,
                currentGameUiState = currentGameUiState
            )
        }
    }

}