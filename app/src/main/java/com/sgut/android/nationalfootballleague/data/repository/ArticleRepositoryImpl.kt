package com.sgut.android.nationalfootballleague.data.repository

import com.sgut.android.nationalfootballleague.asDomain
import com.sgut.android.nationalfootballleague.data.db.SportsDataBase
import com.sgut.android.nationalfootballleague.data.remote.api.SportsApi
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticleDomianModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import com.sgut.android.nationalfootballleague.domain.repositories.ArticleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    val sportsApi: SportsApi,
    val sportsDataBase: SportsDataBase,
    val ioDispatcher: CoroutineDispatcher
) : ArticleRepository {
    override suspend fun getArticles(sport: String, league: String): ArticlesListModel {
        return  withContext(ioDispatcher) {
            val articleResponse = sportsApi.getArticles(sport, league)
            if (articleResponse.isSuccessful) {
                return@withContext articleResponse.body()?.asDomain() ?: ArticlesListModel()
            } else {
                ArticlesListModel()
            }
        }
    }
    override suspend fun getGameArticle(sport: String, league: String, articleId: String): ArticleDomianModel {
       sportsApi.getArticleDetail(articleId)
        return ArticleDomianModel()
    }

    override fun getArticlesListAsFlow(sport: String, league: String): Flow<ArticlesListModel> {
        TODO("Not yet implemented")
    }


}