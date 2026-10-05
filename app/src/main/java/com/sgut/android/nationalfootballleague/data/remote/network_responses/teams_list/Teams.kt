package com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomainModel
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.TeamsModel
import kotlinx.serialization.Serializable


@Serializable
data class Teams(

  @SerialName("team")
  val teamSingle: Team = Team(),

  )

fun Teams.asDomain(): TeamsModel {
  return TeamsModel(
    team = teamSingle.asDomainModel()
  )
}