package com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.GameDetailsLink
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticleDomainModel

data class NewsModel(
    val header: String = "",
    val link: GameDetailsLink? = GameDetailsLink(),
    val articles: List<ArticleDomainModel> = listOf(), // same as ArticleDomainModel

)
