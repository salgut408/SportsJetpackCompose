package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardClockModel
import kotlinx.serialization.Serializable


@Serializable
data class ClockScoreboard(

  @SerialName("value")
  val value: Int? = null,
  @SerialName("displayValue")
  val displayValue: String? = null,

  )

fun ClockScoreboard.asDomain(): ScoreboardClockModel {
  return ScoreboardClockModel(
    value = value ?: 0,
    displayValue = displayValue ?: ""
  )
}