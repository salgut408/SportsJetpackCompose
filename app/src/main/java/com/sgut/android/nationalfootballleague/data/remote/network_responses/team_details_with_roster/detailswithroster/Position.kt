package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsPositionModel
import kotlinx.serialization.Serializable


@Serializable
data class Position(

  @SerialName("id") val id: String = "",
  @SerialName("name") val name: String = "",
  @SerialName("displayName") val displayName: String = "",
  @SerialName("abbreviation") val abbreviation: String = "",
  @SerialName("leaf") val leaf: Boolean? = null,
  @SerialName("parent") val parent: Parent3? = Parent3(),

  )

fun Position.asDomain(): GameDetailsPositionModel {
  return GameDetailsPositionModel(
    name = name,
    displayName = displayName,
    abbreviation =  abbreviation
  )
}