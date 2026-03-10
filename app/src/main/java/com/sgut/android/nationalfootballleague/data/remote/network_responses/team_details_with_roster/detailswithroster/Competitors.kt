package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.CompetitorsModel
import kotlinx.serialization.Serializable


@Serializable
data class Competitors3(

  @SerialName("id")
  val id: String? = null,
  @SerialName("type")
  val type: String? = null,
  @SerialName("order")
  val order: Int? = null,
  @SerialName("homeAway")
  val homeAway: String? = null,
  @SerialName("team")
  val team: Team3? = Team3(),

  )

fun Competitors3.asDomain(): CompetitorsModel {
  return CompetitorsModel(
    id = id ?: "",
    type = type ?: "",
    order = order ?: 0,
    homeAway = homeAway ?: "",
    team = team?.asDomain()
  )
}