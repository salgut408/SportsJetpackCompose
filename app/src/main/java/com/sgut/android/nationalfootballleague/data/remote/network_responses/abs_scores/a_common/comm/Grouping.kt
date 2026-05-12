package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm

import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.TennisCompetition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Grouping(
    @SerialName("competitions")
    val competitions: List<TennisCompetition> = listOf(),
    @SerialName("grouping")
    val grouping: GroupingX = GroupingX()
)