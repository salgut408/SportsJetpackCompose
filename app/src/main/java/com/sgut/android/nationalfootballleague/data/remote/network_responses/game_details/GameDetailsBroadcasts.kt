package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsBroadcasts (

  @SerialName("type"   ) val type   : GameDetailsType?   = GameDetailsType(),
  @SerialName("market" ) val market : GameDetailsMarket? = GameDetailsMarket(),
  @SerialName("media"  ) val media  : GameDetailsMedia?  = GameDetailsMedia(),
  @SerialName("lang"   ) val lang   : String? = null,
  @SerialName("region" ) val region : String? = null,
  @SerialName("station" ) val station : String? = null,



  )