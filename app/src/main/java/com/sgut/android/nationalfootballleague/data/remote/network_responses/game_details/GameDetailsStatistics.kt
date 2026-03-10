package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsStatisticModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsStatistics(

  @SerialName("name")
  val name: String? = null,
  @SerialName("displayValue")
  val displayValue: String? = null,
  @SerialName("label")
  val label: String? = null,

  )
fun GameDetailsStatistics.asDomain(): GameDetailsStatisticModel {
  return GameDetailsStatisticModel(
    name = name ?: "",
    displayValue = displayValue ?: "",
    label = label ?: ""
  )
}