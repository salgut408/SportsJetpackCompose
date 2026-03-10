package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsRecordModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsRecord(

  @SerialName("type")
  val type: String? = null,
  @SerialName("summary")
  val summary: String? = null,
  @SerialName("displayValue")
  val displayValue: String? = null,

  )

fun GameDetailsRecord.asDomain(): GameDetailsRecordModel {
  return GameDetailsRecordModel(
    type = type ?: "",
    summary = summary ?: "",
    displayValue = displayValue ?: ""
  )
}