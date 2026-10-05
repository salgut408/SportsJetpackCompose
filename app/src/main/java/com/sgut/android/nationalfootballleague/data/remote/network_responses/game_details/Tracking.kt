package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Tracking (

  @SerialName("sportName"    ) var sportName    : String? = null,
  @SerialName("leagueName"   ) var leagueName   : String? = null,
  @SerialName("coverageType" ) var coverageType : String? = null,
  @SerialName("trackingName" ) var trackingName : String? = null,
  @SerialName("trackingId"   ) var trackingId   : String? = null

)