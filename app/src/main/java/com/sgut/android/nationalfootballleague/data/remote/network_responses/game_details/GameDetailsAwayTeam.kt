package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
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