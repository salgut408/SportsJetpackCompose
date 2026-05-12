package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TimeRestrictions (

  @SerialName("embargoDate"    ) var embargoDate    : String? = null,
  @SerialName("expirationDate" ) var expirationDate : String? = null

)