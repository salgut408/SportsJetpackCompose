package com.sgut.android.nationalfootballleague.data.repository

import android.util.Log
import com.sgut.android.nationalfootballleague.data.domainmodels.ScoreboardResponseEventModel
import com.sgut.android.nationalfootballleague.data.domainmodels.TeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.data.domainmodels.TeamDomainModel
import com.sgut.android.nationalfootballleague.data.dtomappers.NetworkScoreboardToDomainModelMapper
import com.sgut.android.nationalfootballleague.data.dtomappers.NetworkToTeamDomainModelMapper
import com.sgut.android.nationalfootballleague.data.dtomappers.TeamDetailNetworkToModelMapper
import com.sgut.android.nationalfootballleague.data.dtomappers.TeamDetailWithRosterMapper
import com.sgut.android.nationalfootballleague.data.remote.api.EspnApi
import javax.inject.Inject

class EspnRepository @Inject constructor(
    val teamDomainModelMapper: NetworkToTeamDomainModelMapper,
    val espnApi: EspnApi,
    val teamDetailNetworkToModelMapper: TeamDetailNetworkToModelMapper,
    val rosterMapper: TeamDetailWithRosterMapper,
    val scoreboardDomainMapper: NetworkScoreboardToDomainModelMapper,
) {

    suspend fun getScoreboardResponse(): ScoreboardResponseEventModel {
        val response = espnApi.getWorldCupScoreboard()
        if
                (response.isSuccessful) {
            val scoreBoardresponse = espnApi.getWorldCupScoreboard().body()
            Log.e("Scoreboard resp repo", "response succ $scoreBoardresponse")
            return scoreboardDomainMapper.mapToDomainModel(scoreBoardresponse!!)
        } else {
            Log.e(javaClass.name, response.errorBody().toString())

        }
        val result = espnApi.getWorldCupScoreboard().body()
        return scoreboardDomainMapper.mapToDomainModel(result!!)
    }



    suspend fun getTeams(): List<TeamDomainModel> {
        val response = espnApi.getAllNflTeams()

        if (response.isSuccessful) {
            val teamsResponse =
                espnApi.getAllNflTeams().body()?.sports?.get(0)?.leagues?.get(0)?.teams
            Log.e("tag", "Response successful")
            return teamDomainModelMapper.toDomainList(teamsResponse!!)
        } else {
            Log.e(javaClass.name, response.errorBody().toString())
        }
        val result =
            espnApi.getAllNflTeams().body()?.sports?.getOrNull(0)?.leagues?.getOrNull(0)?.teams
        return teamDomainModelMapper.toDomainList(result!!)
    }

    suspend fun getAllCollegeTeams(): List<TeamDomainModel> {
        val response = espnApi.getAllCollegeTeams()
        if (response.isSuccessful) {
            val teamsResponse =
                espnApi.getAllCollegeTeams().body()?.sports?.get(0)?.leagues?.get(0)?.teams
            Log.e("tag", "Response successful")
            return teamDomainModelMapper.toDomainList(teamsResponse!!)
        } else {
            Log.e(javaClass.name, response.errorBody().toString())
        }
        val result =
            espnApi.getAllCollegeTeams().body()?.sports?.getOrNull(0)?.leagues?.getOrNull(0)?.teams
        return teamDomainModelMapper.toDomainList(result!!)
    }

    suspend fun getAllBaseballTeams(): List<TeamDomainModel> {
        val response = espnApi.getAllBaseballTeams()
        if (response.isSuccessful) {
            val teamsResponse =
                espnApi.getAllBaseballTeams().body()?.sports?.get(0)?.leagues?.get(0)?.teams
            Log.e("tag", "Response successful")
            return teamDomainModelMapper.toDomainList(teamsResponse!!)
        } else {
            Log.e(javaClass.name, response.errorBody().toString())
        }
        val result =
            espnApi.getAllBaseballTeams().body()?.sports?.getOrNull(0)?.leagues?.getOrNull(0)?.teams
        return teamDomainModelMapper.toDomainList(result!!)
    }


    suspend fun getAllHockeyTeams(): List<TeamDomainModel> {
        val response = espnApi.getAllHockeyTeams()
        if (response.isSuccessful) {
            val teamsResponse =
                espnApi.getAllHockeyTeams().body()?.sports?.get(0)?.leagues?.get(0)?.teams
            Log.e("tag", "Response successful")
            return teamDomainModelMapper.toDomainList(teamsResponse!!)
        } else {
            Log.e(javaClass.name, response.errorBody().toString())
        }
        val result =
            espnApi.getAllHockeyTeams().body()?.sports?.getOrNull(0)?.leagues?.getOrNull(0)?.teams
        return teamDomainModelMapper.toDomainList(result!!)
    }

    suspend fun getAllBasketballTeams(): List<TeamDomainModel> {
        val response = espnApi.getAllBasketballTeams()
        if (response.isSuccessful) {
            val teamsResponse =
                espnApi.getAllBasketballTeams().body()?.sports?.get(0)?.leagues?.get(0)?.teams
            Log.e("tag", "Response successful")
            return teamDomainModelMapper.toDomainList(teamsResponse!!)
        } else {
            Log.e(javaClass.name, response.errorBody().toString())
        }
        val result = espnApi.getAllBasketballTeams()
            .body()?.sports?.getOrNull(0)?.leagues?.getOrNull(0)?.teams
        return teamDomainModelMapper.toDomainList(result!!)
    }


    suspend fun getAllWomensBasketballTeams(): List<TeamDomainModel> {
        val response = espnApi.getAllWomensBasketballTeams()
        if (response.isSuccessful) {
            val teamsResponse =
                espnApi.getAllWomensBasketballTeams().body()?.sports?.get(0)?.leagues?.get(0)?.teams
            Log.e("tag", "Response successful")
            return teamDomainModelMapper.toDomainList(teamsResponse!!)
        } else {
            Log.e(javaClass.name, response.errorBody().toString())
        }
        val result = espnApi.getAllBasketballTeams()
            .body()?.sports?.getOrNull(0)?.leagues?.getOrNull(0)?.teams
        return teamDomainModelMapper.toDomainList(result!!)
    }

    suspend fun getAllSoccerTeams(): List<TeamDomainModel> {
        val response = espnApi.getAllSoccerTeams()
        if (response.isSuccessful) {
            val teamsResponse =
                espnApi.getAllSoccerTeams().body()?.sports?.get(0)?.leagues?.get(0)?.teams
            Log.e("tag", "Response successful")
            return teamDomainModelMapper.toDomainList(teamsResponse!!)
        } else {
            Log.e(javaClass.name, response.errorBody().toString())
        }
        val result =
            espnApi.getAllSoccerTeams().body()?.sports?.getOrNull(0)?.leagues?.getOrNull(0)?.teams
        return teamDomainModelMapper.toDomainList(result!!)
    }

    suspend fun getAllWorldCupTeams(): List<TeamDomainModel> {
        val response = espnApi.getAllFifaSoccerTeams()
        if (response.isSuccessful) {
            val teamsResponse =
                espnApi.getAllFifaSoccerTeams().body()?.sports?.get(0)?.leagues?.get(0)?.teams
            Log.e("tag", "Response successful")
            return teamDomainModelMapper.toDomainList(teamsResponse!!)
        } else {
            Log.e(javaClass.name, response.errorBody().toString())
        }
        val result = espnApi.getAllFifaSoccerTeams()
            .body()?.sports?.getOrNull(0)?.leagues?.getOrNull(0)?.teams
        return teamDomainModelMapper.toDomainList(result!!)
    }

    suspend fun getSpecificNflTeam(team: String): TeamDetailWithRosterModel {
        val response = espnApi.getSpecificNflTeam(team)
        if (response.isSuccessful) {
            val teamDetailResponse = espnApi.getSpecificNflTeam(team).body()?.team
            Log.e("repository spefici team", "Response successful $teamDetailResponse")
            return rosterMapper.mapToDomainModel(teamDetailResponse!!)
        } else {
            Log.e(javaClass.name, response.errorBody().toString())
        }
        val result = espnApi.getSpecificNflTeam(team).body()?.team
        return rosterMapper.mapToDomainModel(result!!)
    }

    suspend fun getSpecificTeam(
        sport: String,
        league: String,
        team: String,
    ): TeamDetailWithRosterModel {
        val response = espnApi.getSpecificNflTeam(team)
        if (response.isSuccessful) {
            val teamDetailResponse = espnApi.getSpecificTeam(sport, league, team).body()?.team
            Log.e("TEAM DEBUG-repo", "Response successful $teamDetailResponse")
            return rosterMapper.mapToDomainModel(teamDetailResponse!!)
        } else {
            Log.e("TEAM DEBUG-repo", response.errorBody().toString())
        }
        val result = espnApi.getSpecificTeam(sport, league, team).body()?.team
        return rosterMapper.mapToDomainModel(result!!)
    }

    suspend fun getGeneralScoreboardResponse(sport: String, league: String): ScoreboardResponseEventModel {
        val response = espnApi.getGeneralScoreboard(sport, league)
        if
                (response.isSuccessful) {
            val scoreBoardresponse = espnApi.getGeneralScoreboard(sport, league).body()
            Log.e("Scoreboard resp repo", "response succ $scoreBoardresponse")
            return scoreboardDomainMapper.mapToDomainModel(scoreBoardresponse!!)
        } else {
            Log.e(javaClass.name, response.errorBody().toString())

        }
        val result = espnApi.getGeneralScoreboard(sport, league).body()
        return scoreboardDomainMapper.mapToDomainModel(result!!)
    }

}