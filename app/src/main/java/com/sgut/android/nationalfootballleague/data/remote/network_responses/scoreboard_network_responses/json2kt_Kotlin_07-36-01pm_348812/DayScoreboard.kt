package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DayScoreboard (

  @SerialName("date" )
  val date : String? = null

)