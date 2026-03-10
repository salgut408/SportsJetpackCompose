package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsSeason (

  @SerialName("year" ) var year : Int? = null,
  @SerialName("type" ) var type : Int? = null

)