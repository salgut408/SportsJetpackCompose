package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.toDomainModelList
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailsLogoModel
import kotlinx.serialization.Serializable


@Serializable
data class Logos3(

  @SerialName("href") val href: String? = null,
  @SerialName("width") val width: Int? = null,
  @SerialName("height") val height: Int? = null,
  @SerialName("alt") val alt: String? = null,
  @SerialName("rel") val rel: List<String> = listOf(),
  @SerialName("lastUpdated") val lastUpdated: String? = null,

  )

fun Logos3.asDomain() : FullTeamDetailsLogoModel {
  return FullTeamDetailsLogoModel(
    href = href ?: "",
    width = width ?: 0,
    height = height ?: 0
  )
}

fun List<Logos3>.toDomainModelList(initial: List<Logos3>): List<FullTeamDetailsLogoModel> {
  return initial.map { it.asDomain() }
}


