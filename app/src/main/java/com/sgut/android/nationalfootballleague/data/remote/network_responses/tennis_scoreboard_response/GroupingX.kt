package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.GroupingTennisModelX
import kotlinx.serialization.Serializable

@Serializable
data class GroupingX(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("slug")
    val slug: String = ""
)

fun GroupingX.asDomain(): GroupingTennisModelX {
    return GroupingTennisModelX(
        displayName = displayName,
        id = id,
        slug = slug
    )
}