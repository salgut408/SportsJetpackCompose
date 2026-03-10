package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsLink (

  @SerialName("language"   ) var language   : String?           = null,
  @SerialName("rel"        ) var rel        : ArrayList<String> = arrayListOf(),
  @SerialName("href"       ) var href       : String?           = null,
  @SerialName("text"       ) var text       : String?           = null,
  @SerialName("shortText"  ) var shortText  : String?           = null,
  @SerialName("isExternal" ) var isExternal : Boolean?          = null,
  @SerialName("isPremium"  ) var isPremium  : Boolean?          = null

)