package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsStatModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsStats(

  @SerialName("name")
  val name: String = "",
  @SerialName("displayName")
  val displayName: String = "",
  @SerialName("shortDisplayName")
  val shortDisplayName: String = "",
  @SerialName("description")
  val description: String = "",
  @SerialName("abbreviation")
  val abbreviation: String = "",
  @SerialName("type")
  val type: String = "",
  @SerialName("value")
  val value: Double = 0.0,
  @SerialName("displayValue")
  val displayValue: String = "",

  )

fun GameDetailsStats.asDomain(): GameDetailsStatModel {
  return GameDetailsStatModel(
    name = name,
    displayName = displayName,
    shortDisplayName = shortDisplayName,
    description = description,
    abbreviation = abbreviation,
    type = type,
    value = value,
    displayValue = displayValue
  )
}