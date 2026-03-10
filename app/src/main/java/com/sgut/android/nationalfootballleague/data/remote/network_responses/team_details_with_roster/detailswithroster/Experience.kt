package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Experience (

  @SerialName("years" ) var years : Int? = null

)