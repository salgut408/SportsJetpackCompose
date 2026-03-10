package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardFormatModel
import kotlinx.serialization.Serializable


@Serializable
data class FormatScoreboard(

  @SerialName("regulation")
  val regulation: RegulationScoreboard? = RegulationScoreboard(),

  )

fun FormatScoreboard.asDomain(): ScoreboardFormatModel {
  return ScoreboardFormatModel(
    regulation = regulation?.asDomain()
  )
}