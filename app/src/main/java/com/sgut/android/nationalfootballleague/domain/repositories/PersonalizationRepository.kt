package com.sgut.android.nationalfootballleague.domain.repositories

import com.sgut.android.nationalfootballleague.data.db.league.LeagueDbObj
import com.sgut.android.nationalfootballleague.data.db.sport.SportDbObj
import com.sgut.android.nationalfootballleague.data.db.team.TeamDbObj

interface PersonalizationRepository {
    suspend fun saveSportAsFavorite(sport: SportDbObj)
    suspend fun saveLeagueAsFavorite(league: LeagueDbObj)
    suspend fun saveTeamAsFavorite(team: TeamDbObj)

    suspend fun getFavoriteSports(): List<SportDbObj>
    suspend fun getFavoriteLeagues(): List<LeagueDbObj>
    suspend fun getFavoriteTeams(): List<TeamDbObj>
}