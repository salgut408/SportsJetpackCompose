package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
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