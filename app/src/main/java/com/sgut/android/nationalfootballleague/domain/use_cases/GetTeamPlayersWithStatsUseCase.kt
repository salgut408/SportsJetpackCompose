package com.sgut.android.nationalfootballleague.domain.use_cases

import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_stats.Stats
import com.sgut.android.nationalfootballleague.domain.repositories.TeamDetailsRepository
//                  TODO finish stats per game player repo

data class PlayerWithStats(val name: String = "Sal", val stats: List<Stats>)

class GetTeamPlayersWithStatsUseCase(
    private val teamDetailsRepository: TeamDetailsRepository,
    private val playerDetailsRepository: TeamDetailsRepository, // replace w player repo,
    private val team: String,
    private val league: String,
    private val sport: String
) {
    suspend fun invoke(): List<PlayerWithStats> {
        val players = teamDetailsRepository.getSpecificTeam(league, team, sport ).athletes
        val response: MutableList<PlayerWithStats> = mutableListOf()
        for (player in players) {
            val player = playerDetailsRepository //.getPlayer
//            response.add(PlayerWithStats(player, stats))

        }
        return response
    }

}