package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsMetrics (

  @SerialName("count" ) var count : Int?    = null,
  @SerialName("type"  ) var type  : String? = null

)