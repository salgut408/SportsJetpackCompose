package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsTeamModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsTeams(

  @SerialName("team")
  val team: GameDetailsTeam? = GameDetailsTeam(),
  @SerialName("statistics")
  val statistics: List<GameDetailsStatistics> = listOf(),

  )

fun GameDetailsTeams.asDomain(): GameDetailsTeamModel {
  return GameDetailsTeamModel(
    team = team?.asDomain(),
    statistics = statistics.map { it.asDomain() }
  )
}