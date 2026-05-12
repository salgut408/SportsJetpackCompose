package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ArticleLinks(

  @SerialName("api")
  val api: ArticleApi? = ArticleApi(),
  @SerialName("web")
  val web: ArticleWeb? = ArticleWeb(),
  @SerialName("mobile")
  val mobile: ArticleMobile? = ArticleMobile(),

  )