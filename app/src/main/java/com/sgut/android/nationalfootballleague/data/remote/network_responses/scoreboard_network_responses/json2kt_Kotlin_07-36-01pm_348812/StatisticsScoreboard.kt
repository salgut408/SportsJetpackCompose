package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardStatisticModel
import kotlinx.serialization.Serializable


@Serializable
data class StatisticsScoreboard(

  @SerialName("name")
  val name: String? = null,
  @SerialName("abbreviation")
  val abbreviation: String? = null,
  @SerialName("displayValue")
  val displayValue: String? = null,

  )

fun StatisticsScoreboard.asDomain(): ScoreboardStatisticModel {
  return ScoreboardStatisticModel(
    name = name ?: "",
    abbreviation = abbreviation ?: "",
    displayValue = displayValue ?: ""
  )
}