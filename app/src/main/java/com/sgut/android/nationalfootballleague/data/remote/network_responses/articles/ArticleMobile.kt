package com.sgut.android.nationalfootballleague.data.remote.network_responses.articles

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ArticleMobile (

  @SerialName("leagues" )
  val leagues : ArticleLeagues? = ArticleLeagues()

)