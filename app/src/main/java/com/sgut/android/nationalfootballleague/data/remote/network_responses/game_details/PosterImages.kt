package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PosterImages (

  @SerialName("default" ) var default : Default? = Default(),
  @SerialName("full"    ) var full    : Full?    = Full(),
  @SerialName("wide"    ) var wide    : Wide?    = Wide(),
  @SerialName("square"  ) var square  : Square?  = Square()

)