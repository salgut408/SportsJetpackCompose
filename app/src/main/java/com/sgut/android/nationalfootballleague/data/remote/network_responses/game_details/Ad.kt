package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Ad (

  @SerialName("sport"  )
  val sport  : String = "",
  @SerialName("bundle" )
  val bundle : String = ""
)