package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsMarket (

  @SerialName("id"   ) var id   : String? = null,
  @SerialName("type" ) var type : String? = null

)