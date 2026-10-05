package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomainModel
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailResponseModel
import kotlinx.serialization.Serializable


@Serializable
data class TeamDetailResponse2 (

  @SerialName("team") var fullTeam : Team3? = Team3()

)

fun TeamDetailResponse2.asDomainModel(): FullTeamDetailResponseModel {
  return FullTeamDetailResponseModel(
    fullTeam = fullTeam?.asDomain()!!
  )
}