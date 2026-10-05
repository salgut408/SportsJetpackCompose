package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BirthPlace(
  @SerialName("city") var city: String? = null,
  @SerialName("state") var state: String? = null,
  @SerialName("country") var country: String? = null,
  )