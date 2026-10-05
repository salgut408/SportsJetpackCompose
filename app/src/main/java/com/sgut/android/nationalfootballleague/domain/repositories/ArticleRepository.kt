package com.sgut.android.nationalfootballleague.domain.repositories

import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel

interface ArticleRepository {
    suspend fun getArticles(sport: String, league: String): ArticlesListModel
}