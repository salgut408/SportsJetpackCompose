package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Images3 (

  @SerialName("href"   ) var href   : String?           = null,
  @SerialName("width"  ) var width  : Int?              = null,
  @SerialName("height" ) var height : Int?              = null,
  @SerialName("alt"    ) var alt    : String?           = null,
  @SerialName("rel"    ) var rel    : List<String> = listOf()

)