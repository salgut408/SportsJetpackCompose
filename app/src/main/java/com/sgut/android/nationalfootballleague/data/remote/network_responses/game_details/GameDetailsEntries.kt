package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsEntries (

  @SerialName("team"  ) var team  : String?          = null,
  @SerialName("link"  ) var link  : String?          = null,
  @SerialName("id"    ) var id    : String?          = null,
  @SerialName("uid"   ) var uid   : String?          = null,
  @SerialName("stats" ) var stats : List<GameDetailsStats> = listOf(),
  @SerialName("logo"  ) var logo  : List<GameDetailsLogo>  = listOf()

)