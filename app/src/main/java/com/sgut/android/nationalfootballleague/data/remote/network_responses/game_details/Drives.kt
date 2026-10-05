package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.Previous
import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.DrivesModel
import kotlinx.serialization.Serializable

@Serializable
data class Drives (

    @SerialName("previous" )
    val previous : List<Previous> = listOf()


)

fun Drives.asDomain(): DrivesModel {
    return DrivesModel(
        previous = previous.map { it.asDomain() }
    )
}