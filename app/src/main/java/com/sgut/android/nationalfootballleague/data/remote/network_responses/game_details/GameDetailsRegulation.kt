package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsRegulationModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsRegulation(

  @SerialName("periods")
  val periods: Int? = null,
  @SerialName("displayName")
  val displayName: String? = null,
  @SerialName("slug")
  val slug: String? = null,
  @SerialName("clock")
  val clock: Double? = null,

  )

fun GameDetailsRegulation.asDomain(): GameDetailsRegulationModel {
  return GameDetailsRegulationModel(
    periods = periods ?: 0,
    displayName = displayName ?: "",
    slug = slug ?: "",
    clock = clock?.toInt() ?: 0
  )
}