package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AddressScoreboard (

  @SerialName("city"    ) val city    : String? = null,
  @SerialName("country" ) val country : String? = null

)