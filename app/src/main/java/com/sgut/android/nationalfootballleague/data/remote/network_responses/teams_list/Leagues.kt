package com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list

import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomainModel
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.LeagueModel
import kotlinx.serialization.Serializable


@Serializable
data class Leagues(

  @SerialName("id") val id: String? = null,
  @SerialName("uid") val uid: String? = null,
  @SerialName("name") val name: String? = null,
  @SerialName("abbreviation") val abbreviation: String? = null,
  @SerialName("shortName") val shortName: String? = null,
  @SerialName("slug") val slug: String? = null,
  @SerialName("teams") val teams: List<Teams>? = null,

  )

fun Leagues.toDomain(): LeagueModel {
  return LeagueModel(
    id = id ?: "",
    uid = uid ?: "",
    name = name ?: "",
    abbreviation = abbreviation ?: "",
    shortName = shortName ?: "",
    slug = slug ?: "",
    teams = teams?.map { it.teamSingle.asDomainModel() } ?: listOf()
  )
}