package com.sgut.android.nationalfootballleague.data.repository

import com.sgut.android.nationalfootballleague.data.db.SportsDataBase
import com.sgut.android.nationalfootballleague.data.db.league.LeagueDbObj
import com.sgut.android.nationalfootballleague.data.db.sport.SportDbObj
import com.sgut.android.nationalfootballleague.data.db.team.TeamDbObj
import com.sgut.android.nationalfootballleague.domain.repositories.PersonalizationRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class PersonalizationRepositoryImpl @Inject constructor(
    val sportsDataBase: SportsDataBase,
    val ioDispatcher: CoroutineDispatcher
): PersonalizationRepository {
    override suspend fun saveSportAsFavorite(sport: SportDbObj) {
        TODO("Not yet implemented")
    }

    override suspend fun saveLeagueAsFavorite(league: LeagueDbObj) {
        TODO("Not yet implemented")
    }

    override suspend fun saveTeamAsFavorite(team: TeamDbObj) {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteSports(): List<SportDbObj> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteLeagues(): List<LeagueDbObj> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteTeams(): List<TeamDbObj> {
        TODO("Not yet implemented")
    }

}