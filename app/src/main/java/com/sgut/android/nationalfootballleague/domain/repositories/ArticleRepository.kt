package com.sgut.android.nationalfootballleague.domain.repositories

import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticleDomianModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getArticles(sport: String, league: String): ArticlesListModel
    // use game details article api part
    suspend fun getGameArticle(sport: String, league: String, articleId: String): ArticleDomianModel

    fun getArticlesListAsFlow(sport: String, league: String): Flow<ArticlesListModel>

}