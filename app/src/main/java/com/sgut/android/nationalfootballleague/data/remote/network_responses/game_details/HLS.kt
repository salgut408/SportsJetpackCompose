package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class HLS (

  @SerialName("href" ) var href : String? = null,
  @SerialName("HD"   ) var HD   : HD?     = HD()

)