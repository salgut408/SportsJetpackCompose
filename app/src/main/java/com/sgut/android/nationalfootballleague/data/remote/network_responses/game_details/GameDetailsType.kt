package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsTypeModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsType(

  @SerialName("id")
  val id: String = "",
  @SerialName("name")
  val name: String = "",
  @SerialName("completed")
  val completed: Boolean = false,
  @SerialName("description")
  val description: String = "",
  @SerialName("detail")
  val gameTimeDetail: String = "",
  @SerialName("shortDetail")
  val shortGameTimeDetail: String = "",
  @SerialName("text")
  val text: String = "",
  @SerialName("abbreviation")
  val abbreviation: String = "",
  @SerialName("state")
  val state: StatusState? = StatusState.PRE,


  )

fun GameDetailsType.asDomain(): GameDetailsTypeModel {
  return GameDetailsTypeModel(
    name = name,
    completed = completed,
    description = description,
    gameTimeDetail = gameTimeDetail,
    shortGameTimeDetail = shortGameTimeDetail,
    text = text,
    abbreviation = abbreviation,
    statusState = state
  )
}