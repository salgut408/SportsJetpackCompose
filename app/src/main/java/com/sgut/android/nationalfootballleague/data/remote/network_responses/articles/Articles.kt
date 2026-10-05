package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticleDomainModel
import kotlinx.serialization.Serializable


@Serializable
data class Articles(

    @SerialName("dataSourceIdentifier")
    val dataSourceIdentifier: String? ="",
    @SerialName("images")
    val images: List<ArticleImages> = listOf(),
    @SerialName("description")
    val description: String? = "",
    @SerialName("published")
    val published: String? = "",
    @SerialName("type")
    val type: String? = "",
    @SerialName("premium")
    val premium: Boolean? = false,
    @SerialName("links")
    val links: ArticleLinks? = ArticleLinks(),
    @SerialName("lastModified")
    val lastModified: String? = "",
    @SerialName("headline")
    val headline: String? = "",
    @SerialName("byline")
    val byline: String? = "",

    )

fun Articles.asDomain(): ArticleDomainModel {
    return ArticleDomainModel(
      images = images.map { it.asDomain() },
      description = description,
      published = published ?: "",
      headline = headline ?:"",
      byline = byline ?:"",
        links = links ?: ArticleLinks(),
        dataSourceIdentifier = dataSourceIdentifier?:""
    )
}