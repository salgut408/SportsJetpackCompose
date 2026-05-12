package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.SeasonModel
import kotlinx.serialization.Serializable


@Serializable
data class Season3(

  @SerialName("year")
  val year: Int? = null,
  @SerialName("displayName")
  val displayName: String? = null,
  )

fun Season3.asDomain(): SeasonModel {
  return SeasonModel(
    year = year ?: 0,
    displayName = displayName ?: ""
  )
}