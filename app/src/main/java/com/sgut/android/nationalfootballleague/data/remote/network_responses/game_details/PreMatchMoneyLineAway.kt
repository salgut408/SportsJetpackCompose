package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PreMatchMoneyLineAway (

  @SerialName("oddId"      ) var oddId      : String? = null,
  @SerialName("value"      ) var value      : String? = null,
  @SerialName("betSlipUrl" ) var betSlipUrl : String? = null

)