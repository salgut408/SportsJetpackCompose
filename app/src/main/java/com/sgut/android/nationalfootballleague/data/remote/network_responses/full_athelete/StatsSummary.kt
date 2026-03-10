package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatsSummary(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("statistics")
    val statistics: List<Statistic> = listOf()
)