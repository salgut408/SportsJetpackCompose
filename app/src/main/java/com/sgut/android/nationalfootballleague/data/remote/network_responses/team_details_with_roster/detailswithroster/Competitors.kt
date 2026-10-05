package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
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