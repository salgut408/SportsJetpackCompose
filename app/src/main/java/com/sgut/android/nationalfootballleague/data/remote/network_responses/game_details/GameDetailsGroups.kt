package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsGroups(

  @SerialName("standings")
  val standings: GameDetailsStandings? = GameDetailsStandings(),
  @SerialName("header")
  val header: String? = null,
  @SerialName("href")
  val href: String? = null,

  )