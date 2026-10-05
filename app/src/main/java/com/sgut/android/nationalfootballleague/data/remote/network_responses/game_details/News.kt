package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.Articles
import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomainModel
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.Team
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomainModel
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.*
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.BasketballCoordinateModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.BasketballPlayModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.NewsModel
import kotlinx.serialization.Serializable

@Serializable
data class News(

    @SerialName("header")
    val header: String? = null,
    @SerialName("link")
    val link: GameDetailsLink? = GameDetailsLink(),
    @SerialName("articles")
    val articles: List<Articles> = listOf(), // same as ArticleDomainModel

)

fun News.asDomain(): NewsModel {
    return NewsModel(
        header = header ?: "",
        link = link,
        articles = articles.map { it.asDomain() }
    )
}


@Serializable
data class NetworkPlays(

    @SerialName("id")
  val id: String? = null,
    @SerialName("sequenceNumber")
  val sequenceNumber: String? = null,
    @SerialName("text")
  val text: String? = null,
    @SerialName("awayScore")
  val awayScore: Int? = null,
    @SerialName("homeScore")
  val homeScore: Int? = null,
    @SerialName("period")
  val period: Period? = Period(),
    @SerialName("clock")
  val clock: Clock? = Clock(),
    @SerialName("scoringPlay")
  val scoringPlay: Boolean? = null,
    @SerialName("scoreValue")
  val scoreValue: Int? = null,
    @SerialName("team")
  val team: Team? = Team(),
    @SerialName("wallclock")
  val wallclock: String? = null,
    @SerialName("shootingPlay")
  val shootingPlay: Boolean? = null,
    @SerialName("coordinate")
  val coordinate: Coordinate? = Coordinate(),

    )

fun NetworkPlays.asDomain(): BasketballPlayModel {
    return BasketballPlayModel(
        id = id ?: "",
        text = text ?: "",
        awayScore = awayScore ?: 0,
        homeScore = homeScore ?: 0,
        period = period?.number ?: 0,
        clock = clock?.displayValue ?: "",
        scoringPlay = scoringPlay ?: false,
        scoreValue = scoreValue ?: 0,
        team = team?.asDomainModel(),
        wallclock = wallclock ?: "",
        coordinate = coordinate?.asDomain()
    )
}


@Serializable
data class Coordinate(

  @SerialName("x")
  val x: Int? = null,
  @SerialName("y")
  val y: Int? = null,

  )

fun Coordinate.asDomain(): BasketballCoordinateModel {
    return BasketballCoordinateModel(
        x = x ?: 0,
        y = y ?: 0
    )
}