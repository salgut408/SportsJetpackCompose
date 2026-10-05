package com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardRecordModel
import kotlinx.serialization.Serializable


@Serializable
data class RecordsScoreboard(

  @SerialName("name")
  val name: String? = null,
  @SerialName("type")
  val type: String? = null,
  @SerialName("summary")
  val summary: String? = null,
  @SerialName("abbreviation")
  val abbreviation: String? = null,

  )

fun RecordsScoreboard.asDomain(): ScoreboardRecordModel {
  return ScoreboardRecordModel(
    name = name ?: "",
    type = type ?: "",
    summary = summary ?: "",
    abbreviation = abbreviation ?: ""
  )
}