package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardTeamModel
import kotlinx.serialization.Serializable


@Serializable
data class TeamScoreboard(
    @SerialName("id")
    val id: String = "",
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("logo")
    val logo: String = "",
    @SerialName("color")
    val color: String = "",
    @SerialName("displayName")
    var displayName: String = "",
    @SerialName("score")
    val score: Int = 0,
    @SerialName("shortDisplayName")
    val shortDisplayName: String = "",

    )

fun TeamScoreboard.asDomain(): ScoreboardTeamModel {
    return ScoreboardTeamModel(
        id = id,
        abbreviation = abbreviation,
        name = name,
        logo = logo,
        color = color,
        displayName = displayName,
        score = score,
        shortDisplayName = shortDisplayName
    )
}