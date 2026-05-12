package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.*
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsBoxscore(
  @SerialName("teams")
  val teams: List<GameDetailsTeams> = listOf(),
  @SerialName("players")
  val players: List<Players> = listOf(),
  @SerialName("statistics")
  val statistics: List<GameDetailsStatistics> = listOf(),
  )

fun GameDetailsBoxscore.asDomain(): BoxScoreModel {
    return BoxScoreModel(
        teams = teams.map { it.asDomain() },
        players = players.map { it.asDomain() },
        statistics = statistics.map { it.asDomain() },
    )
}


@Serializable
data class Players(
  @SerialName("team")
  val team: GameDetailsTeam? = GameDetailsTeam(),
  @SerialName("statistics")
  val statistics: List<Statistics> = listOf(),
)

fun Players.asDomain(): BoxscorePlayerModel {
  return BoxscorePlayerModel(
    team = team?.asDomain() ?: GameDetailsTeamInfoModel(),
    statistics = statistics.map { it.asDomain() }
  )
}

@Serializable
data class Statistics(

  @SerialName("name")
  val name: String? = null,
  @SerialName("keys")
  val keys: List<String> = listOf(),
  @SerialName("text")
  val text: String? = null,
  @SerialName("labels")
  val labels: List<String> = listOf(),
  @SerialName("descriptions")
  val descriptions: List<String> = listOf(),
  @SerialName("athletes")
  val athletes: List<GameDetailsAthletes> = listOf(),
  @SerialName("totals")
  val totals: List<String> = listOf(),

  )

fun Statistics.asDomain(): BoxscorePlayerStatisticModel {
  return BoxscorePlayerStatisticModel(
    name = name ?: "",
    keys = keys,
    text = text ?: "",
    descriptions = descriptions,
    athletes = athletes.map { it.asDomain() },
    totals = totals
  )
}

@Serializable
data class GameDetailsAthletes(

  @SerialName("athlete")
  val athlete: GameDetailsAthlete? = GameDetailsAthlete(),
  @SerialName("stats")
  val stats: List<String> = listOf(),
  )

fun GameDetailsAthletes.asDomain(): GameDetailsAthleteModel {
  return GameDetailsAthleteModel(
    athlete = athlete?.asDomain(),
    stats = stats
  )
}
