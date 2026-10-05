package com.sgut.android.nationalfootballleague.data.remote.network_responses.articles

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ArticleApi(

  @SerialName("leagues")
  val leagues: ArticleLeagues? = ArticleLeagues(),

  )