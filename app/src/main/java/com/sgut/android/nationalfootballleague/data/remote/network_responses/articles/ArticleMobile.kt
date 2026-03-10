package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ArticleMobile (

  @SerialName("leagues" )
  val leagues : ArticleLeagues? = ArticleLeagues()

)