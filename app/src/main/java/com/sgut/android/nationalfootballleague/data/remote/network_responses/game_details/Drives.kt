package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.Previous
import com.sgut.android.nationalfootballleague.asDomain
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