package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.WinprobabilityModel
import kotlinx.serialization.Serializable


@Serializable
data class Winprobability(

    @SerialName("tiePercentage")
    val tiePercentage: Double? = null,
    @SerialName("homeWinPercentage")
    val homeWinPercentage: Double? = null,
    @SerialName("secondsLeft")
    val secondsLeft: Double? = null,
    @SerialName("playId")
    val playId: String? = null,

    )

fun Winprobability.asDomain(): WinprobabilityModel {
    return WinprobabilityModel(
        tiePercentage = tiePercentage ?: 0.0,
        homeWinPercentage = homeWinPercentage ?: 0.0,
        secondsLeft = secondsLeft ?: 0.0,
        playId = playId ?: ""
    )
}