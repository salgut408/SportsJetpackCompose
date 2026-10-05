package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.*
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsLeaders(

  @SerialName("team")
  val team: GameDetailsTeam = GameDetailsTeam(),
  @SerialName("leaders")
  val leaders: List<GameDetailsLeaders2> = listOf(),
  )
fun GameDetailsLeaders.asDomain(): GameDetailsLeadersModel {
  return GameDetailsLeadersModel(
    team = team.asDomain(),
    leaders = leaders.map { it.asDomain() }
  )
}

@Serializable
data class GameDetailsLeaders2(
  @SerialName("name")
  val name: String = "",
  @SerialName("displayName")
  val displayName: String = "",
  @SerialName("leaders")
  val leadersAthlete: List<AthleteLeaders> = listOf(),
  )

@Serializable
data class GameDetailsLeaders4(
  @SerialName("name")
  val name: String = "",
  @SerialName("displayName")
  val displayName: String = "",
  @SerialName("leaders")
  val leadersAthlete: List<AthleteLeaders4> = listOf(),
)

fun GameDetailsLeaders4.asDomain() : GameLeadersModel4 {
  return GameLeadersModel4(
    name = name,
    displayName = displayName,
    leadersAthlete = leadersAthlete.map { it.asDomain() }
  )
}

fun GameDetailsLeaders2.asDomain(): GameLeadersModel {
  return GameLeadersModel(
    name = name,
    displayName = displayName,
    leadersAthlete = leadersAthlete.map { it.asDomain() }
  )
}

@Serializable
data class AthleteLeaders(
  @SerialName("displayValue")
  val displayValue: String = "",
  @SerialName("athlete")
  val athlete: GameDetailsAthlete = GameDetailsAthlete(),

  )

@Serializable
data class AthleteLeaders4(
  @SerialName("displayValue")
  val displayValue: String = "",
  @SerialName("athlete")
  val athlete: GameDetailsAthlete4 = GameDetailsAthlete4(),

  )

fun AthleteLeaders4.asDomain(): AthleteLeaderModel4 {
  return AthleteLeaderModel4(
    displayValue = displayValue,
    athlete = athlete.asDomain()
  )
}

fun AthleteLeaders.asDomain(): AthleteLeaderModel {
  return AthleteLeaderModel(
    displayValue = displayValue,
    athlete = athlete.asDomain()
  )
}


