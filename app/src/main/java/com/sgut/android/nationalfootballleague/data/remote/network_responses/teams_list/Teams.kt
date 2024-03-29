package com.sgut.android.nationalfootballleague

import com.google.gson.annotations.SerializedName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.TeamsModel


data class Teams(

  @SerializedName("team")
  val teamSingle: Team = Team(),

  )

fun Teams.asDomain(): TeamsModel {
  return TeamsModel(
    team = teamSingle.asDomainModel()
  )
}