package com.sgut.android.nationalfootballleague

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