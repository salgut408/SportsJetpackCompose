package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Groups3 (

  @SerialName("id"           ) var id           : String?  = null,
  @SerialName("parent"       ) var parent       : Parent3?  = Parent3(),
  @SerialName("isConference" ) var isConference : Boolean? = null

)