package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardCompetitorsModel
import kotlinx.serialization.Serializable


@Serializable
data class CompetitorScoreboard(
  @SerialName("id")
  val id: String? = null,
  @SerialName("uid")
  val uid: String? = null,
  @SerialName("type")
  val type: String? = null,
  @SerialName("linescores")
  val linescores: List<Linescore>? = listOf(),
  @SerialName("order")
  val order: Int? = null,
  @SerialName("homeAway")
  val homeAway: String? = null,
  @SerialName("winner")
  val winner: Boolean? = null,
  @SerialName("form")
  val form: String? = null,
  @SerialName("score")
  val score: String? = null,
  @SerialName("records")
  val records: List<RecordsScoreboard> = listOf(),
  @SerialName("team")
  val team: TeamScoreboard = TeamScoreboard(),
  @SerialName("statistics")
  val statistics: List<StatisticsScoreboard> = listOf(),
  @SerialName("leaders")
  val leaders: List<GameDetailsLeaders4> = listOf(),

//  TODO Fix probables w Probable v probableS
//  @SerialName("probables")
//  val probables: List<Probables> = listOf(),

  )

@Serializable
data class Linescore(
  @SerialName("value")
  val value: Double? = 0.0
)

@Serializable
data class Probable(
  @SerialName("name")
  val name: String? = "",
  @SerialName("displayName")
  val displayName: String? = "",
  @SerialName("abbreviation")
  val abbreviation: String? = "",
  @SerialName("playerId")
  val playerId: String? = "",
  @SerialName("athlete")
  val athlete: ProbableAthlete? = null

)

@Serializable
data class ProbableAthlete(
  @SerialName("id")
  val id: String? = "",
  @SerialName("fullName")
  val fullName: String? = "",
  @SerialName("displayName")
  val displayName: String? = "",
  @SerialName("shortName")
  val shortName: String? = "",
)

fun CompetitorScoreboard.asDomain(): ScoreboardCompetitorsModel {
  return ScoreboardCompetitorsModel(
    id = id ?: "",
    uid = uid ?: "",
    type =  type ?: "",
    order = order ?: 0,
    homeAway = homeAway ?: "",
    winner = winner ?: false,
    score = score ?: "",
    records = records.map { it.asDomain() },
    team = team.asDomain(),
    statistics = statistics.map { it.asDomain() },
    leaders = leaders.map { it.asDomain() },
    linescores = linescores
  )
}