package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticleImageModel
import kotlinx.serialization.Serializable


@Serializable
data class ArticleImages(

  @SerialName("name")
  val name: String? = null,
  @SerialName("width")
  val width: Int? = null,
  @SerialName("id")
  val id: Int? = null,
  @SerialName("credit")
  val credit: String? = null,
  @SerialName("type")
  val type: String? = null,
  @SerialName("url")
  val url: String? = null,
  @SerialName("height")
  val height: Int? = null,

  )

fun ArticleImages.asDomain(): ArticleImageModel {
    return ArticleImageModel(
      width = width ?: 0,
      id = id ?: 0,
      credit = credit ?: "",
      url = url ?: "",
      height = height ?: 0
    )
}