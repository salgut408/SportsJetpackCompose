package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.FullTeamsListsModel
import kotlinx.serialization.Serializable


@Serializable
data class NFLTeamsResponse (
  @SerialName("sports" ) val sports : List<Sports>?
)


fun NFLTeamsResponse.toDomain(): FullTeamsListsModel {
  return FullTeamsListsModel(
    sport = sports?.get(0)?.toDomain()!!
  )
}


