package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsStandings(

  @SerialName("fullViewLink")
  val fullViewLink: FullViewLink? = FullViewLink(),
  @SerialName("groups")
  val groups: List<GameDetailsGroups> = listOf(),

  )