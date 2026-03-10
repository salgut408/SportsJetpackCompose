package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsFormatModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsOvertimeModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsRegulationModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsFormat(

  @SerialName("regulation")
  val regulation: GameDetailsRegulation? = GameDetailsRegulation(),
  @SerialName("overtime")
  val overtime: GameDetailsOvertime? = GameDetailsOvertime(),

  )

fun GameDetailsFormat.asDomain(): GameDetailsFormatModel {
  return GameDetailsFormatModel(
    regulation = regulation?.asDomain() ?: GameDetailsRegulationModel(),
    overtime = overtime?.asDomain() ?: GameDetailsOvertimeModel()
  )
}