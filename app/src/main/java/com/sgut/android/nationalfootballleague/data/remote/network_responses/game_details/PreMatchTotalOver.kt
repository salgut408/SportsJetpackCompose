package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PreMatchTotalOver (

  @SerialName("oddId"      ) var oddId      : String? = null,
  @SerialName("value"      ) var value      : String? = null,
  @SerialName("betSlipUrl" ) var betSlipUrl : String? = null

)