package com.sgut.android.nationalfootballleague.data.repository

import com.sgut.android.nationalfootballleague.asDomain
import com.sgut.android.nationalfootballleague.data.remote.api.SportsApi
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import com.sgut.android.nationalfootballleague.domain.repositories.ArticleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val sportsApi: SportsApi,
    private val ioDispatcher: CoroutineDispatcher,
) : ArticleRepository {

    override suspend fun getArticles(sport: String, league: String): ArticlesListModel =
        withContext(ioDispatcher) {
            val response = sportsApi.getArticles(sport, league)
            if (!response.isSuccessful) {
                error("Articles request failed: HTTP ${response.code()} for $sport/$league")
            }
            response.body()?.asDomain() ?: ArticlesListModel()
        }
}