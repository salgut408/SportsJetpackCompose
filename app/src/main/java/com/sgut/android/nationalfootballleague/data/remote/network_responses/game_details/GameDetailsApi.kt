package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsApi (

  @SerialName("leagues" ) var leagues : GameDetailsLeagues? = GameDetailsLeagues()

)