package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerSwitcher(
    @SerialName("athletes")
    val athletes: List<AthleteX> = listOf(),
    @SerialName("filters")
    val filters: List<Filter> = listOf(),
    @SerialName("links")
    val links: List<LinkXX> = listOf(),
    @SerialName("team")
    val team: TeamX = TeamX()
)