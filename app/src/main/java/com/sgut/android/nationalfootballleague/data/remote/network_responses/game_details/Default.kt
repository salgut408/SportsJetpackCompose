package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Default (

  @SerialName("href"   ) var href   : String? = null,
  @SerialName("width"  ) var width  : Int?    = null,
  @SerialName("height" ) var height : Int?    = null

)