package com.sgut.android.nationalfootballleague

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