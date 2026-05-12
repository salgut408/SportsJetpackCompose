package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Mobile (

  @SerialName("leagues" ) var leagues : Leagues? = Leagues()

)