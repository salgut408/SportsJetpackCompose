package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsHomeTeamModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsHomeTeam (

  @SerialName("id" )
  val id : String? = null,
  @SerialName("gameProjection" )
  val gameProjection : Float? = null,
  @SerialName("teamChanceLoss" )
  val teamChanceLoss : Float? = null,
  @SerialName("teamChanceTie" )
  val teamChanceTie : Float? = null,

  )
fun GameDetailsHomeTeam.asDomain(): GameDetailsHomeTeamModel {
  return GameDetailsHomeTeamModel(
    id = id,
    gameProjection = gameProjection,
    teamChanceLoss = teamChanceLoss,
    teamChanceTie = teamChanceTie,
  )

}
