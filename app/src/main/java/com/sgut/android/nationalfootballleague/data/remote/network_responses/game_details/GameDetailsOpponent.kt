package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsOpponent (

  @SerialName("id"           ) var id           : String?          = null,
  @SerialName("uid"          ) var uid          : String?          = null,
  @SerialName("displayName"  ) var displayName  : String?          = null,
  @SerialName("abbreviation" ) var abbreviation : String?          = null,
  @SerialName("links"        ) var links        : ArrayList<GameDetailsLinks> = arrayListOf(),
  @SerialName("logo"         ) var logo         : String?          = null

)