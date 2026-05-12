package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsHeadshotModel
import kotlinx.serialization.Serializable


@Serializable
data class Headshot (

  @SerialName("href" ) val href : String? = null,
  @SerialName("alt"  ) val alt  : String? = null

)

fun Headshot.asDomain(): GameDetailsHeadshotModel {
  return GameDetailsHeadshotModel(
    href = href ?: "",
    alt = alt ?: ""
  )
}