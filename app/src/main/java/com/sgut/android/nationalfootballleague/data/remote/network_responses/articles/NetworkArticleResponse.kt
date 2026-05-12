package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import kotlinx.serialization.Serializable


@Serializable
data class NetworkArticleResponse(
  @SerialName("header")
  val header: String = "",
  @SerialName("articles")
  val articles: List<Articles> = listOf(),
  )

fun NetworkArticleResponse.asDomain(): ArticlesListModel {
  return ArticlesListModel(
    header = header,
    articles = articles.map { it.asDomain() }
  )
}