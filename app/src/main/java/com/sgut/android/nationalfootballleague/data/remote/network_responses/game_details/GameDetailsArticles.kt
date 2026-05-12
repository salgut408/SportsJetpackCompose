package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsArticles (

  @SerialName("images"       ) var images       : List<GameDetailsImages>     = listOf(),
  @SerialName("description"  ) var description  : String?               = null,
  @SerialName("published"    ) var published    : String?               = null,
  @SerialName("type"         ) var type         : String?               = null,
  @SerialName("premium"      ) var premium      : Boolean?              = null,
  @SerialName("links"        ) var links        : GameDetailsLinks?                = GameDetailsLinks(),
  @SerialName("lastModified" ) var lastModified : String?               = null,
  @SerialName("categories"   ) var categories   : List<GameDetailsCategories> = listOf(),
  @SerialName("headline"     ) var headline     : String?               = null

)