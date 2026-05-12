package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsStatusModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsStatus(

  @SerialName("type")
  val type: GameDetailsType? = GameDetailsType(),
  @SerialName("periodPrefix")
  val periodPrefix: InningPrefix? =InningPrefix.PRE,
  )

@Serializable
enum class InningPrefix {
  @SerialName("Mid")
  MID,
  @SerialName("Top")
  TOP,
  @SerialName("Pre")
  PRE,
  @SerialName("Bottom")
  BOTTOM,
  @SerialName("End")
  END,
  @SerialName("Start")
  START
}

fun GameDetailsStatus.asDomain(): GameDetailsStatusModel {
  return GameDetailsStatusModel(
    type = type?.asDomain(),
    periodPrefix = periodPrefix
  )
}