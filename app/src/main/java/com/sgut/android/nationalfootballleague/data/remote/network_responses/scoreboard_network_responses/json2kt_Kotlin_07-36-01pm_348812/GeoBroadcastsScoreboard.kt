package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GeoBroadcastsScoreboard (

  @SerialName("type"   ) var type   : TypeScoreboard?   = TypeScoreboard(),
  @SerialName("market" ) var market : MarketScoreboard? = MarketScoreboard(),
  @SerialName("media"  ) var media  : MediaScoreboard?  = MediaScoreboard(),
  @SerialName("lang"   ) var lang   : String? = null,
  @SerialName("region" ) var region : String? = null

)