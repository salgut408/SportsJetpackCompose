package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsLeagueModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsLeague(

    @SerialName("id")
    val id: Int? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("name")
    val name: String = "",
    @SerialName("abbreviation")
    val abbreviation: String = "",

)

fun GameDetailsLeague.asDomain(): GameDetailsLeagueModel {
    return GameDetailsLeagueModel(
        id = id ?: 0,
        description = description,
        name = name,
        abbreviation = abbreviation
    )
}