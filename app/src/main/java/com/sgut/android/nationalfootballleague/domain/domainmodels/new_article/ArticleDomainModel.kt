package com.sgut.android.nationalfootballleague.domain.domainmodels.new_article

import com.sgut.android.nationalfootballleague.ArticleLinks

data class ArticleDomainModel(
    val images: List<ArticleImageModel> = listOf(), // needs model
    val description: String? = "",
    val published: String = "",
    val headline: String = "",
    val byline: String? = "",
    val links: ArticleLinks = ArticleLinks(),
    val dataSourceIdentifier: String = ""

    )
