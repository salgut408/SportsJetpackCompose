package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.WeekModel
import kotlinx.serialization.Serializable


@Serializable
data class Week3(

  @SerialName("number")
  val number: Int? = null,
  @SerialName("text")
  val text: String? = null,
  )

fun Week3.asDomain(): WeekModel {
  return WeekModel(
    number = number ?: 0,
    text = text ?: ""
  )
}