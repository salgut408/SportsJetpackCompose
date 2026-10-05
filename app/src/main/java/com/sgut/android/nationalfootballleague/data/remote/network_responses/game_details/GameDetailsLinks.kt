package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsLinks(

  @SerialName("api")
  val api: GameDetailsApi? = GameDetailsApi(),
  @SerialName("web")
  val web: Web? = Web(),
  @SerialName("mobile")
  val mobile: Mobile? = Mobile(),
  @SerialName("source")
  val source: Source2? = Source2(),
  )

@Serializable
data class Source2(
  @SerialName("mezzanine")
  val mezzanine: Mezzanine? = Mezzanine(),

  )

