package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.SeasonTypeModel
import kotlinx.serialization.Serializable


@Serializable
data class SeasonType3(
  @SerialName("id")
  val id: String? = null,
  @SerialName("type")
  val type: Int? = null,
  @SerialName("name")
  val name: String? = null,
  @SerialName("abbreviation")
  val abbreviation: String? = null,
  )

fun SeasonType3.asDomain(): SeasonTypeModel {
  return SeasonTypeModel(
    id = id ?: "",
    type = type ?: 0,
    name = name ?: "",
    abbreviation = abbreviation ?: ""
  )
}