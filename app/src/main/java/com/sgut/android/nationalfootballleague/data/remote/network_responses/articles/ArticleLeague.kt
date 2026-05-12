package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ArticleLeague(

  @SerialName("id")
  val id: Int = 0,
  @SerialName("description")
  val description: String = "",
  @SerialName("links")
  val links: ArticleLinks? = ArticleLinks(),

  )