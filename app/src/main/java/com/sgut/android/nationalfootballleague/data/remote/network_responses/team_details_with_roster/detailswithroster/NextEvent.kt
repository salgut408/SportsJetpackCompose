package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.NextEventModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.SeasonTypeModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.WeekModel
import kotlinx.serialization.Serializable


@Serializable
data class NextEvent3(

  @SerialName("id") val id: String = "",
  @SerialName("date") val date: String = "",
  @SerialName("name") val name: String = "",
  @SerialName("shortName") val shortName: String = "",
  @SerialName("season") val season: Season3 = Season3(),
  @SerialName("seasonType") val seasonType: SeasonType3? = SeasonType3(),
  @SerialName("week") val week: Week3 = Week3(),
  @SerialName("timeValid") val timeValid: Boolean = false,
  @SerialName("competitions") val competitions: List<Competitions3> = listOf(),
  @SerialName("links") val links: List<Links3> = listOf(),

  )

fun NextEvent3.asDomain(): NextEventModel {
  return NextEventModel(
    id = id,
    date = date,
    name = name,
    shortName = shortName,
    season = season.asDomain(),
    seasonType = seasonType?.asDomain() ?: SeasonTypeModel(),
    week = week.asDomain() ?: WeekModel(),
    competitions = competitions.map { it.asDomain() }
  )
}