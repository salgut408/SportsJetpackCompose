package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomainModel
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.Team
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomainModel
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.*
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.RosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.RostersModel
import kotlinx.serialization.Serializable

@Serializable
data class Rosters(
    @SerialName("homeAway") val homeAway: String = "",
    @SerialName("winner") val winner: Boolean = false,
    @SerialName("team") val team: Team = Team(),
    @SerialName("roster") val roster: List<Roster> = arrayListOf(),
)

fun Rosters.asDomain(): RostersModel {
    return RostersModel(
        homeAway = homeAway,
        winner = winner,
        team = team.asDomainModel(),
        roster = roster.map { it.asDomain() }
    )
}

@Serializable
data class Roster(
    @SerialName("active")
    val active: Boolean = false,
    @SerialName("starter")
    val starter: Boolean = false,
    @SerialName("athlete")
    val athlete: GameDetailsAthlete = GameDetailsAthlete(),
    @SerialName("position")
    val position: GameDetailsPosition = GameDetailsPosition(),
    @SerialName("batOrder")
    val batOrder: Int = 0,
//    @SerialName("subbedIn")
//    val subbedIn: Boolean = false,
//    @SerialName("subbedOut")
//    val subbedOut: Boolean = false,
    @SerialName("stats")
    val stats: List<GameDetailsStats> = listOf(),
    @SerialName("jersey")
    var jersey: String = "",

    )

fun Roster.asDomain(): RosterModel {
    return RosterModel(
        active = active,
        starter = starter,
        athlete = athlete.asDomain(),
        position = position.asDomain(),
        batOrder = batOrder,
//        subbedIn = subbedIn,
//        subbedOut = subbedOut,
        stats = stats.map { it.asDomain() },
        jersey = jersey
    )
}


