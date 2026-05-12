package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DeviceRestrictions (

  @SerialName("type"    ) var type    : String?           = null,
  @SerialName("devices" ) var devices : ArrayList<String> = arrayListOf()

)