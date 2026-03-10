package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardRegulationModel
import kotlinx.serialization.Serializable


@Serializable
data class RegulationScoreboard (

  @SerialName("periods")
  val periods : Int? = null

)
fun RegulationScoreboard.asDomain(): ScoreboardRegulationModel {
  return ScoreboardRegulationModel(
    periods = periods ?: 0
  )
}