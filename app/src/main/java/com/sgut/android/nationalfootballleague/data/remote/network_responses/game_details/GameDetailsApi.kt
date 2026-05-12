package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsApi (

  @SerialName("leagues" ) var leagues : GameDetailsLeagues? = GameDetailsLeagues()

)