package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.nationalfootballleague.domain.domainmodels.GameDetailModel
import com.sgut.android.nationalfootballleague.data.repository.EspnRepositoryImpl
import com.sgut.android.nationalfootballleague.ui.screens.teamdetails.HexToJetpackColor2
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val espnRepository: EspnRepositoryImpl,
): ViewModel() {


    private val _gameDetailUiState = MutableStateFlow(GameDetailsScreenUiState())

    var gameDetailUiState: StateFlow<GameDetailsScreenUiState> = _gameDetailUiState.asStateFlow()

    private val _sealedGameDetailUiState = MutableStateFlow(GameDetailModel())

    val sealedGameDetailUiState: StateFlow<GameDetailModel> = _sealedGameDetailUiState.asStateFlow()

     var _colorsTeamList: MutableList<Color> = mutableListOf()


    init {

    }


    private fun render(viewState: SealedGameDetailUiState) {
        when(viewState) {
            Loading -> {}
            Error -> {}
            is GameDetailsLoaded -> {}
            Success -> TODO()
        }
    }

    fun loadGameDetails(sport: String, league: String, event: String) = viewModelScope.launch {
        try {
            val result = espnRepository.getGameDetails(sport, league, event)
            setGameDetailsUiState(result, sport, league, )
                        Log.i("DEBUGDETAIL vm", result.toString())
            result.boxscore?.teams?.forEach { i->
                _colorsTeamList.add(HexToJetpackColor2.getColor(i.team?.color ?: "Color"))
                Log.i("DEBUGDETAIL vm", _colorsTeamList.toString())
            }
            _colorsTeamList.add(Color.Black)

        } catch (e: Exception){
            Log.e("DEBUG DETAILS", e.message.toString())
        }
    }

    fun onSaveArticleClick(article: GameDetailModel)= viewModelScope.launch {
        espnRepository.saveArticle(article)

    }


    fun setGameDetailsUiState(gameDetails: GameDetailModel, currentSport: String, currentLeague: String) {
        _gameDetailUiState.update {
            it.copy(currentLeague  = currentLeague,
                currentSport = currentSport,
                currentGameDetails = gameDetails
            )
        }
    }


}