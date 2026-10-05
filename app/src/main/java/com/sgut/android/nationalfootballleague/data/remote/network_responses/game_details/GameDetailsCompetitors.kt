package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsCompetitorModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsCompetitors(

  @SerialName("id")
  val id: String? = null,
  @SerialName("uid")
  val uid: String? = null,
  @SerialName("order")
  val order: Int? = null,
  @SerialName("homeAway")
  val isHomeOrAway: String? = null,
  @SerialName("team")
  val team: GameDetailsTeam? = GameDetailsTeam(),
  @SerialName("record")
  val record: List<GameDetailsRecord> = listOf(),
  @SerialName("possession")
  val possession: Boolean = false,
  @SerialName("score")
  val score: Int = 0,
  @SerialName("probables")
val probables: List<Probables> = listOf(),

)

fun GameDetailsCompetitors.asDomain(): GameDetailsCompetitorModel {
  return GameDetailsCompetitorModel(
    id = id ?: "" ,
    order = order ?: 0,
    isHomeOrAway = isHomeOrAway ?: "",
    team = team?.asDomain(),
    record = record.map { it.asDomain() },
    possession = possession,
    score = score,
    probables = probables.map { it.asDomain() }
  )
}