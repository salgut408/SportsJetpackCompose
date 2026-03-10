package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Media (

  @SerialName("shortName" ) var shortName : String? = null

)