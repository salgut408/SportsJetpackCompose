package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.StatusDomainModel
import kotlinx.serialization.Serializable


@Serializable
data class Status3(

  @SerialName("clock")
  val clock: Double? = null,
  @SerialName("displayClock")
  val displayClock: String? = null,
  @SerialName("period")
  val period: Int? = null,
  @SerialName("type")
  val type: Type3? = Type3(),
  )
fun Status3.asDomain(): StatusDomainModel {
  return StatusDomainModel(
    clock = clock.toString(),
    displayClock = displayClock ?: "",
    period = period ?: 0,
    type = type?.asDomain()
  )
}