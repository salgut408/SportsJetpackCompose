package com.sgut.android.nationalfootballleague.data.repository

import com.sgut.android.nationalfootballleague.data.db.article.ArticleDao
import com.sgut.android.nationalfootballleague.data.help.GenNetworkFlow
import com.sgut.android.nationalfootballleague.data.remote.api.SportsApi
import javax.inject.Inject

class NewArticleRepository @Inject constructor(
    private val articleDao: ArticleDao,
    private val sportsApi: SportsApi,
    private val genericNetworkFlow: GenNetworkFlow,
) {

    fun getArticles(sport: String, league: String) = genericNetworkFlow.getNetworkBoundResourceResult(
        dbQuery = { articleDao.getAllSavedArticles() },
        networkApiCall = {sportsApi.getArticles(sport, league)},
//        saveCallResult = {articleDao.insertArticle(it.first())}
    )
}