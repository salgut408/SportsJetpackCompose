package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.baseball


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeaturedAthlete(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("athlete")
    val athlete: AthleteXXX = AthleteXXX(),
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("playerId")
    val playerId: Int = 0,
    @SerialName("shortDisplayName")
    val shortDisplayName: String = "",
    @SerialName("statistics")
    val statistics: List<StatisticX> = listOf(),
    @SerialName("team")
    val team: TeamX = TeamX()
)