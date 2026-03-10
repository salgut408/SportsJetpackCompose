package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Streaming (

  @SerialName("href" ) var href : String? = null

)