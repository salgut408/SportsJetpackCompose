package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsAthleteDetailsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsModel

/**
 * Sealed UI state for the Game Details screen.
 *
 *  - [Loading] is the initial state before the first successful fetch.
 *  - [Content] carries everything the screen needs to render: the game model,
 *    the sport/league context (used by sport-specific renderers like the
 *    baseball situation card), and the players map (athlete IDs → details).
 *  - [Error] surfaces a failure message that the screen can show inline.
 */
sealed interface GameDetailsUiState {
    data object Loading : GameDetailsUiState

    data class Content(
        val sport: String,
        val league: String,
        val game: GameDetailsModel,
        val players: Map<String, GameDetailsAthleteDetailsModel> = emptyMap(),
    ) : GameDetailsUiState

    data class Error(val message: String) : GameDetailsUiState
}