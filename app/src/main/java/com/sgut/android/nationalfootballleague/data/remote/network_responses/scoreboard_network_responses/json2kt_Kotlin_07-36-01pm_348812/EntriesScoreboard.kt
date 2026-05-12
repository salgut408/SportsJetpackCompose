package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class EntriesScoreboard (

  @SerialName("label"     ) var label     : String? = null,
  @SerialName("detail"    ) var detail    : String? = null,
  @SerialName("value"     ) var value     : String? = null,
  @SerialName("startDate" ) var startDate : String? = null,
  @SerialName("endDate"   ) var endDate   : String? = null

)