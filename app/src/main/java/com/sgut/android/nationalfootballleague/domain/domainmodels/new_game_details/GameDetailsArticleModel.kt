package com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.GameDetailsImages
data class GameDetailsArticleModel(
    val description: String = "",
    val headline: String = "",
    val id: Int = 0,
    val images: List<GameDetailsImages> = listOf(),
    val lastModified: String = "",
    val published: String = "",
    val source: String = "",
    val story: String = "",
)
