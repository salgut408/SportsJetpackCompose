package com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.GameDetailsLinks
data class VideoModel(
    val source: String = "",
    val id: Int = 0,
    val headline: String = "",
    val description: String = "",
    val lastModified: String = "",
    val originalPublishDate: String = "",
    val duration: Int = 0,
    val thumbnail: String = "",
    val links: GameDetailsLinks = GameDetailsLinks(),
)
