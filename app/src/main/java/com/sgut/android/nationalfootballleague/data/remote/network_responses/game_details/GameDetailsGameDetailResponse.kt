package com.sgut.android.nationalfootballleague

import com.google.gson.annotations.SerializedName
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.*
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsModel


data class GameDetailResponse(

    @SerializedName("rosters")
    val rosters: List<Rosters> = listOf(),


    @SerializedName("situation")
    val situation: Situation? = Situation(),
    @SerializedName("notes")
    val notes: List<String> = listOf(),


    @SerializedName("boxscore")
    val boxscore: GameDetailsBoxscore? = GameDetailsBoxscore(),
    @SerializedName("format")
    val format: GameDetailsFormat? = GameDetailsFormat(),
    @SerializedName("gameInfo")
    val gameInfo: GameInfo = GameInfo(),
    @SerializedName("lastFiveGames")
    val lastFiveGames: List<LastFiveGames> = listOf(),
    @SerializedName("leaders")
    val leaders: List<GameDetailsLeaders> = listOf(),
    @SerializedName("injuries")
    val injuries: List<GameDetailsInjuries> = listOf(),
    @SerializedName("broadcasts")
    val broadcasts: List<GameDetailsBroadcasts> = listOf(),
    @SerializedName("predictor")
    val predictor: Predictor? = Predictor(),
    @SerializedName("pickcenter")
    val pickcenter: List<Pickcenter> = listOf(),
    @SerializedName("againstTheSpread")
    val againstTheSpread: List<AgainstTheSpread> = listOf(),
    @SerializedName("odds")
    val odds: List<Odds> = listOf(),
    @SerializedName("header")
    val header: Header? = Header(),
    @SerializedName("news")
    val news: News? = News(),
    @SerializedName("article")
    val singleGameArticle: GameDetailsArticle? = GameDetailsArticle(),
    @SerializedName("ticketsInfo")
    val ticketsInfo: GameDetailsTicketsInfo? = GameDetailsTicketsInfo(),
    @SerializedName("standings")
    val standings: GameDetailsStandings? = GameDetailsStandings(),
    @SerializedName("drives")
    val drives: Drives? = Drives(),
    @SerializedName("plays")
    val plays: List<NetworkPlays> = listOf(),
    @SerializedName("winprobability")
    val winprobability: List<Winprobability> = listOf(),
    @SerializedName("scoringPlays")
    val scoringPlays: List<ScoringPlays> = listOf(),
    @SerializedName("videos")
    val videos: List<Videos> = listOf(),
//    @SerializedName("seasonseries")
//    val seasonseries: List<Seasonseries> = listOf(),

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
    )
}

