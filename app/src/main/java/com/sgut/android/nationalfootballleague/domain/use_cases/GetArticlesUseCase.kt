package com.sgut.android.nationalfootballleague.domain.use_cases

import com.sgut.android.nationalfootballleague.data.db.article.ArticleDbObject
import com.sgut.android.nationalfootballleague.data.help.NetworkResult
import com.sgut.android.nationalfootballleague.data.repository.NewArticleRepository
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import com.sgut.android.nationalfootballleague.domain.repositories.ArticleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class GetArticlesUseCase @Inject constructor(
    private val articleRepository: ArticleRepository,
) {
    suspend operator fun invoke(sport: String, league: String): ArticlesListModel {
        return articleRepository.getArticles(sport, league)
    }
}

class NewGetArticlesUseCase @Inject constructor(
    private val newArticleRepository: NewArticleRepository,
){
    suspend operator fun invoke(sport: String, league: String) : Flow<NetworkResult<Flow<List<ArticleDbObject>>>> {
        val news = newArticleRepository.getArticles(sport, league)
        return news
    }
}