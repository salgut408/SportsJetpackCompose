package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.Links
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsArticleModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsArticle(

  @SerialName("keywords")
  val keywords: List<String> = listOf(),
  @SerialName("description")
  val description: String? = null,
  @SerialName("source")
  val source: String? = null,
  @SerialName("video")
  val video: List<Video> = listOf(),
  @SerialName("type")
  val type: String? = null,
  @SerialName("nowId")
  val nowId: String? = null,
  @SerialName("premium")
  val premium: Boolean? = null,
  @SerialName("related")
  val related: List<String> = listOf(),
  @SerialName("allowSearch")
  val allowSearch: Boolean? = null,
  @SerialName("links")
  val links: Links? = Links(),
  @SerialName("id")
  val id: Int? = null,
  @SerialName("categories")
  val categories: List<GameDetailsCategories> = listOf(),
  @SerialName("headline")
  val headline: String? = null,
  @SerialName("gameId")
  val gameId: String? = null,
  @SerialName("images")
  val images: List<GameDetailsImages> = listOf(),
  @SerialName("linkText")
  val linkText: String? = null,
  @SerialName("published")
  val published: String? = null,
  @SerialName("guid")
  val guid: String? = null,
  @SerialName("lastModified")
  val lastModified: String? = null,
  @SerialName("metrics")
  val metrics: List<GameDetailsMetrics> = listOf(),
  @SerialName("story")
  val story: String? = null,

  )

fun GameDetailsArticle.asDomain(): GameDetailsArticleModel {
  return GameDetailsArticleModel(
    description = description ?: "",
    source = source ?: "",
    headline = headline ?: "",
    images = images,
    published = published ?: "",
    lastModified = lastModified ?: "",
    story = story ?: ""

  )
}