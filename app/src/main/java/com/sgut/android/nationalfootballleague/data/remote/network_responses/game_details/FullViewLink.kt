package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class FullViewLink (

  @SerialName("text" ) val text : String? = null,
  @SerialName("href" ) val href : String? = null

)