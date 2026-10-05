package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.SeasonTypeModel
import kotlinx.serialization.Serializable


@Serializable
data class SeasonType3(
  @SerialName("id")
  val id: String? = null,
  @SerialName("type")
  val type: Int? = null,
  @SerialName("name")
  val name: String? = null,
  @SerialName("abbreviation")
  val abbreviation: String? = null,
  )

fun SeasonType3.asDomain(): SeasonTypeModel {
  return SeasonTypeModel(
    id = id ?: "",
    type = type ?: 0,
    name = name ?: "",
    abbreviation = abbreviation ?: ""
  )
}