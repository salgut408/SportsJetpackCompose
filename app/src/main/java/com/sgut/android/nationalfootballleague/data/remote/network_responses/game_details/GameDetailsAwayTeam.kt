package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsAwayTeamModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsAwayTeam (

  @SerialName("id" )
  val id : String? = null,
  @SerialName("gameProjection" )
  val gameProjection : Float? = null,
  @SerialName("teamChanceLoss" )
  val teamChanceLoss : Float? = null,
  @SerialName("teamChanceTie" )
  val teamChanceTie : Float? = null,

)

fun GameDetailsAwayTeam.asDomain(): GameDetailsAwayTeamModel {
  return GameDetailsAwayTeamModel(
    id = id,
    gameProjection = gameProjection,
    teamChanceLoss = teamChanceLoss,
    teamChanceTie = teamChanceTie
  )
}