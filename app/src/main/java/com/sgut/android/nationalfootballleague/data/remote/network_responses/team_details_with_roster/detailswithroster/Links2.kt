package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Links3 (

  @SerialName("language"   ) var language   : String?           = null,
  @SerialName("rel"        ) var rel        : List<String> = listOf(),
  @SerialName("href"       ) var href       : String?           = null,
  @SerialName("text"       ) var text       : String?           = null,
  @SerialName("shortText"  ) var shortText  : String?           = null,
  @SerialName("isExternal" ) var isExternal : Boolean?          = null,
  @SerialName("isPremium"  ) var isPremium  : Boolean?          = null

)