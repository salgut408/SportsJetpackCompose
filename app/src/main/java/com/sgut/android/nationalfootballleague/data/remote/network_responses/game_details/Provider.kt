package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.ProviderModel
import kotlinx.serialization.Serializable


@Serializable
data class Provider(

  @SerialName("id")
  val id: String? = null,
  @SerialName("name")
  val name: String? = null,
  @SerialName("priority")
  val priority: Int? = null,

  )

fun Provider.asDomain(): ProviderModel {
  return ProviderModel(
    id = id ?: "",
    name = name ?: "",
    priority = priority ?: 0
  )
}