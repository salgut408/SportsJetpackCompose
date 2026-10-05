package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.*
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailResponse(

    @SerialName("rosters")
    val rosters: List<Rosters> = listOf(),
    @SerialName("situation")
    val situation: Situation? = Situation(),
    @SerialName("notes")
    val notes: List<String> = listOf(),
    @SerialName("boxscore")
    val boxscore: GameDetailsBoxscore? = GameDetailsBoxscore(),
    @SerialName("format")
    val format: GameDetailsFormat? = GameDetailsFormat(),
    @SerialName("gameInfo")
    val gameInfo: GameInfo = GameInfo(),
    @SerialName("lastFiveGames")
    val lastFiveGames: List<LastFiveGames> = listOf(),
    @SerialName("leaders")
    val leaders: List<GameDetailsLeaders> = listOf(),
    @SerialName("injuries")
    val injuries: List<GameDetailsInjuries> = listOf(),
    @SerialName("broadcasts")
    val broadcasts: List<GameDetailsBroadcasts> = listOf(),
    @SerialName("predictor")
    val predictor: Predictor? = Predictor(),
    @SerialName("pickcenter")
    val pickcenter: List<Pickcenter> = listOf(),
    @SerialName("againstTheSpread")
    val againstTheSpread: List<AgainstTheSpread> = listOf(),
    @SerialName("odds")
    val odds: List<Odds> = listOf(),
    @SerialName("header")
    val header: Header? = Header(),
    @SerialName("news")
    val news: News? = News(),
    @SerialName("article")
    val singleGameArticle: GameDetailsArticle? = GameDetailsArticle(),
    @SerialName("ticketsInfo")
    val ticketsInfo: GameDetailsTicketsInfo? = GameDetailsTicketsInfo(),
    @SerialName("standings")
    val standings: GameDetailsStandings? = GameDetailsStandings(),
    @SerialName("drives")
    val drives: Drives? = Drives(),
    @SerialName("plays")
    val plays: List<NetworkPlays> = listOf(),
    @SerialName("winprobability")
    val winprobability: List<Winprobability> = listOf(),
    @SerialName("scoringPlays")
    val scoringPlays: List<ScoringPlays> = listOf(),
    @SerialName("videos")
    val videos: List<Videos> = listOf(),
    @SerialName("seasonseries")
    val seasonseries: List<Seasonseries> = listOf(),
    @SerialName("meta")
    val meta: GameMeta? = null,
    @SerialName("wallclockAvailable")
    val wallclockAvailable: Boolean = false,
    @SerialName("baseballPlays")
    val baseballPlays: List<BaseballPlay> = listOf(),
    )

fun GameDetailResponse.asDomain(): GameDetailsModel {
    return GameDetailsModel(
        baseballSituation = situation?.asDomain(),
        boxscore = boxscore?.asDomain(),
        format = format?.asDomain(),
        gameInfo = gameInfo.asDomain(),
        lastFiveGames = lastFiveGames.map { it.asDomain() },
        leaders = leaders.map { it.asDomain() },
        injuries = injuries.map { it.asDomain() },
        broadcasts = broadcasts,
        predictor = predictor?.asDomain(),
        pickcenter = pickcenter.map { it.asDomain() },
        againstTheSpread = againstTheSpread.map { it.asDomain() },
        odds = odds.map { it.asDomain() },
        header = header?.asDomain(),
        news = news?.asDomain(),
        singleGameArticle = singleGameArticle?.asDomain(),
        ticketsInfo = ticketsInfo?.asDomain(),
        standings = standings,
        drives = drives?.asDomain(),
        winprobability = winprobability.map { it.asDomain() },
        scoringPlays = scoringPlays.map { it.asDomain() },
        videos = videos.map { it.asDomain() },
        plays = plays.map { it.asDomain() },
        rosters = rosters.map { it.asDomain() },
        seasonseries = seasonseries.map { it.asDomain() },
        meta = meta?.asDomain(),
        wallclockAvailable = wallclockAvailable,
        baseballPlays = baseballPlays.map { it.asDomain() },
    )
}

