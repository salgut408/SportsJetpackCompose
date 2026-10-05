package com.sgut.android.nationalfootballleague.data.remote.network_responses.articles

import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import kotlinx.serialization.Serializable


@Serializable
data class NetworkArticleResponse(
  @SerialName("header")
  val header: String = "",
  @SerialName("articles")
  val articles: List<Articles> = listOf(),
  )

fun NetworkArticleResponse.asDomain(): ArticlesListModel {
  return ArticlesListModel(
    header = header,
    articles = articles.map { it.asDomain() }
  )
}