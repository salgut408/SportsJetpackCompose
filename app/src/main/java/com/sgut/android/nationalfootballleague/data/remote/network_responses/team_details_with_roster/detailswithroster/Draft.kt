package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Draft (

  @SerialName("displayText" ) var displayText : String? = null,
  @SerialName("round"       ) var round       : Int?    = null,
  @SerialName("year"        ) var year        : Int?    = null,
  @SerialName("selection"   ) var selection   : Int?    = null

)