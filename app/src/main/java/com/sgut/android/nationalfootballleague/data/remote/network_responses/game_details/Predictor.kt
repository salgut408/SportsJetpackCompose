package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.PredictorModel
import kotlinx.serialization.Serializable


@Serializable
data class Predictor(

  @SerialName("header")
  val header: String? = null,
  @SerialName("homeTeam")
  val homeTeam: GameDetailsHomeTeam? = GameDetailsHomeTeam(),
  @SerialName("awayTeam")
  val awayTeam: GameDetailsAwayTeam? = GameDetailsAwayTeam(),

  )

fun Predictor.asDomain(): PredictorModel {
  return PredictorModel(
    header = header,
    homeTeam = homeTeam?.asDomain(),
    awayTeam = awayTeam?.asDomain()
  )
}