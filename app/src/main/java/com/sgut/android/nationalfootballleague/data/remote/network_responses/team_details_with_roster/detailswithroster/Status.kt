package com.sgut.android.nationalfootballleague

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