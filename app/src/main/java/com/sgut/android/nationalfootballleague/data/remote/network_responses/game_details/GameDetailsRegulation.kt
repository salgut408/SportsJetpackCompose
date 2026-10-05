package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsRegulationModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsRegulation(

  @SerialName("periods")
  val periods: Int? = null,
  @SerialName("displayName")
  val displayName: String? = null,
  @SerialName("slug")
  val slug: String? = null,
  @SerialName("clock")
  val clock: Double? = null,

  )

fun GameDetailsRegulation.asDomain(): GameDetailsRegulationModel {
  return GameDetailsRegulationModel(
    periods = periods ?: 0,
    displayName = displayName ?: "",
    slug = slug ?: "",
    clock = clock?.toInt() ?: 0
  )
}