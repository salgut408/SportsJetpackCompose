package com.sgut.android.nationalfootballleague.data.remote.api

import com.sgut.android.nationalfootballleague.*
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.ScoreboardData
import com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard.BaseballScoreBoardNetwork
import com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete.NetworkAthleteResponse
import com.sgut.android.nationalfootballleague.data.remote.network_responses.standings.StandingsNetworkResponse
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule.ScheduleResponseNetwork
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_stats.NetworkStat
import com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response.TennisScoresNetwork
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SportsApi {

    @GET("site/v2/sports/{sport}/{league}/news")
    suspend fun getArticles(
        @Path("sport")
        sport: String = "",
        @Path("league")
        league: String = "",
    ): Response<NetworkArticleResponse>

    @GET("site/v2/sports/{sport}/{league}/scoreboard")
    suspend fun getGeneralScoreboard(
        @Path("sport")
        sport: String? = null,
        @Path("league")
        league: String? = null,
    ): Response<NetworkScoreboardResponse>

    @GET("site/v2/sports/{sport}/{league}/scoreboard")
    suspend fun getBaseballScoreboard(
        @Path("sport")
        sport: String? = null,
        @Path("league")
        league: String? = null,
    ): Response<BaseballScoreBoardNetwork>

    @GET("site/v2/sports/{sport}/{league}/scoreboard")
    suspend fun getCollegeBasketballScoreboard(
        @Path("sport")
        sport: String? = null,
        @Path("league")
        league: String? = null,
        @Query("limit")
        limit: String = "500"

    ): Response<NetworkScoreboardResponse>

//    TODO adding limit takes a long time to load teams ?limit=200
    @GET("site/v2/sports/{sport}/{league}/teams")
    suspend fun getTeamsListForLeague(
        @Path("sport")
        sport: String? = null,
        @Path("league")
        league: String? = null,
    ): Response<NFLTeamsResponse>


    @GET("site/v2/sports/{sport}/{league}/teams/{teamAbbreviation}?enable=roster,headshot")
    suspend fun getSpecificTeam(
        @Path("sport")
        sport: String? = null,
        @Path("league")
        league: String? = null,
        @Path("teamAbbreviation")
        teamAbbreviation: String? = null
    ): Response<TeamDetailResponse2>

    @GET("site/v2/sports/{sport}/{league}/summary")
    suspend fun getGameDetails(
        @Path("sport")
        sport: String? = null,
        @Path("league")
        league: String? = null,
        @Query("event") event: String
    ): Response<GameDetailResponse>

    @GET("{articleApi}")
    suspend fun getArticleDetail(
        @Path("articleApi")
        articleApi: String? = null
    ): Response<ArticleDetailNetworkResponse>

// TODO implement getting scoreboard with dates
    @GET("site/v2/sports/{sport}/{league}/scoreboard")
    suspend fun getGeneralScoreboardWithDate(
        @Path("sport")
        sport: String? = null,
        @Path("league")
        league: String? = null,
        @Query("dates") date: String
    ): Response<NetworkScoreboardResponse>

    @GET("site/v2/sports/{sport}/{league}/teams/{teamId}/schedule")
    suspend fun getTeamSchedule(
        @Path("sport")
        sport: String? = null,
        @Path("league")
        league: String? = null,
        @Path("teamId")
        teamId: String? = null,
    ): Response<ScheduleResponseNetwork>


    @GET("common/v3/sports/{sport}/{league}/athletes/{athleteId}")
    suspend fun getAthleteInfo(
        @Path("sport")
        sport: String? = null,
        @Path("league")
        league: String? = null,
        @Path("athleteId")
        athleteId: String? = null,
    ): Response<NetworkAthleteResponse>

    @GET("v2/sports/{sport}/{league}/standings")
    suspend fun getStandings(
        @Path("sport")
        sport: String? = null,
        @Path("league")
        league: String? = null,
        @Query("type") type: String = "0"
    ): Response<StandingsNetworkResponse>

    @GET("site/v2/sports/{sport}/{league}/teams/{team}/statistics")
    suspend fun getStats(
        @Path("sport")
        sport: String? = null,
        @Path("league")
        league: String? = null,
        @Path("team")
        team: String? = null
    ): Response<NetworkStat>



    @GET("site/v2/sports/{sport}/{league}/scoreboard")
    suspend fun getTennisScoreboard(
        @Path("sport")
        sport: String? = null,
        @Path("league")
        league: String? = null,
    ): Response<TennisScoresNetwork>

    @GET("site/v2/sports/{sport}/{league}/scoreboard")
    suspend fun getAbstractScoreboard(
        @Path("sport")
        sport: String? = null,
        @Path("league")
        league: String? = null,
    ): Response<ScoreboardData> // Abstraction



//    TODO get standings - has type Parameters 0 = overall, 1 = wildcard, 2 = Expanded Standings, 3 = "Vs. Division Standings", 4 = Monthly Standings

//    TODO add player info
//    https://site.web.api.espn.com/apis/common/v3/sports/:sport/:league_abbrev/athletes/:athlete_id
//    https://site.web.api.espn.com/apis/common/v3/sports/baseball/mlb/athletes/36928

//    player stats
//    http://www.espn.com/mlb/player/stats/_/id/36928/austin-hays",

//    TODO get team stats
//    https://site.api.espn.com/apis/site/v2/sports/football/nfl/teams/ari/statistics


//      TODO  article details / story
//    https://now.core.api.espn.com/v1/sports/news/36080086
    //d883c277e2153

    // TODO racing API Endpoint F1
//    https://site.api.espn.com/apis/site/v2/sports/racing/f1/scoreboard

//    Tennis:
//    ATP:
//    http://site.api.espn.com/apis/site/v2/sports/tennis/atp/scoreboard
//    http://site.api.espn.com/apis/site/v2/sports/tennis/atp/news
//    WTA:
//    http://site.api.espn.com/apis/site/v2/sports/tennis/wta/scoreboard
//    http://site.api.espn.com/apis/site/v2/sports/tennis/wta/news
//
//    Golf:
//    PGA:
//    http://site.api.espn.com/apis/site/v2/sports/golf/pga/scoreboard
//    http://site.api.espn.com/apis/site/v2/sports/golf/pga/news
//    LPGA:
//    http://site.api.espn.com/apis/site/v2/sports/golf/lpga/scoreboard
//    http://site.api.espn.com/apis/site/v2/sports/golf/lpga/news

//    TODO Standings
//    https://site.api.espn.com/apis/v2/sports/baseball/mlb/standings



}