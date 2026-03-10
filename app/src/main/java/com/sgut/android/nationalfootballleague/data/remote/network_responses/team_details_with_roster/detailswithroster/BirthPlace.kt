package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BirthPlace(
  @SerialName("city") var city: String? = null,
  @SerialName("state") var state: String? = null,
  @SerialName("country") var country: String? = null,
  )