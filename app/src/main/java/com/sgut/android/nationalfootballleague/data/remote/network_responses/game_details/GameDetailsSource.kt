package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsSource (

  @SerialName("href" ) var href : String? = null,
  @SerialName("headline" ) var headline : String? = null,
  @SerialName("thumbnail" ) var thumbnail : String? = null,
  @SerialName("links" ) var links : GameDetailsLinks? = null




)