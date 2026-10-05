package com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DayScoreboard (

  @SerialName("date" )
  val date : String? = null

)