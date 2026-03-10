package com.sgut.android.nationalfootballleague

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