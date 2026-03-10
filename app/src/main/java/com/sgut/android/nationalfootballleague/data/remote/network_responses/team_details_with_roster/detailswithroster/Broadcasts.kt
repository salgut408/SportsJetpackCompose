package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Broadcasts3(

  @SerialName("type") var type: Type3? = Type3(),
  @SerialName("market") var market: Market? = Market(),
  @SerialName("media") var media: Media? = Media(),
  @SerialName("lang") var lang: String? = null,
  @SerialName("region") var region: String? = null,

  )