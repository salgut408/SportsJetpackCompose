package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BroadcastsScoreboard (

  @SerialName("market" ) var market : String?           = null,
  @SerialName("names"  ) var names  : ArrayList<String> = arrayListOf()

)