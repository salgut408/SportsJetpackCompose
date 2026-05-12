package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.GroupingTennisModel
import kotlinx.serialization.Serializable

@Serializable
data class Grouping(
    @SerialName("competitions")
    val competitions: List<Competition> = listOf(),
    @SerialName("grouping")
    val grouping: GroupingX = GroupingX()
)

fun Grouping.asDomain(): GroupingTennisModel {
    return GroupingTennisModel(
        competitions = competitions.map { it.asDomain() },
        grouping = grouping.asDomain()
    )
}