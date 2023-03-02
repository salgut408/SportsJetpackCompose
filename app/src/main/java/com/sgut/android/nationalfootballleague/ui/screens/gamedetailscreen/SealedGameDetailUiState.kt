package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

import com.sgut.android.nationalfootballleague.domain.domainmodels.GameDetailModel

sealed class SealedGameDetailUiState

object Success: SealedGameDetailUiState()
object Loading: SealedGameDetailUiState()
object Error: SealedGameDetailUiState()

data class GameDetailsLoaded(
    var currentSport: String = "",
    var currentLeague: String = "",
    val currentGameDetails: GameDetailModel? = null,
) : SealedGameDetailUiState()

