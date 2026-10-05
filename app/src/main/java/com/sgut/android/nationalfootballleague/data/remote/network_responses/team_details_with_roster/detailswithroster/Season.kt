package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.SeasonModel
import kotlinx.serialization.Serializable


@Serializable
data class Season3(

  @SerialName("year")
  val year: Int? = null,
  @SerialName("displayName")
  val displayName: String? = null,
  )

fun Season3.asDomain(): SeasonModel {
  return SeasonModel(
    year = year ?: 0,
    displayName = displayName ?: ""
  )
}