package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.StatusDomainModel
import kotlinx.serialization.Serializable


@Serializable
data class StatusScoreboard(
  @SerialName("clock")
  val clock: String? = null,
  @SerialName("displayClock")
  val displayClock: String? = null,
  @SerialName("period")
  val period: Int = 0,
  @SerialName("type")
  val type: TypeScoreboard? = TypeScoreboard(),
  )

fun StatusScoreboard.asDomain() : StatusDomainModel {
  return StatusDomainModel(
    clock = clock?.toString() ?: "STATUS CLOCK",
    displayClock = displayClock ?: "",
    period = period,
    type = type?.asDomain()
  )
}