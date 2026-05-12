package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ArticleWeb(

  @SerialName("leagues")
  val leagues: ArticleLeagues? = ArticleLeagues(),

  )