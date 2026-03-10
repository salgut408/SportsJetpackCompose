package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsPositionModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsPosition(

  @SerialName("name")
  val name: String = "",
  @SerialName("displayName")
  val displayName: String = "",
  @SerialName("abbreviation")
  val abbreviation: String = "",

  )

fun GameDetailsPosition.asDomain(): GameDetailsPositionModel {
  return GameDetailsPositionModel(
    name = name ,
    displayName = displayName ,
    abbreviation = abbreviation
  )
}