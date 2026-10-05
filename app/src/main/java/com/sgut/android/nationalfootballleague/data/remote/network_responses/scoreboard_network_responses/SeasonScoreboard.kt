package com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SeasonScoreboard (

  @SerialName("year" )
  val year : Int    = 0,
//  @SerialName("type" )
//  val type : Int    = 0,
  @SerialName("slug" )
  val slug : String = ""

)