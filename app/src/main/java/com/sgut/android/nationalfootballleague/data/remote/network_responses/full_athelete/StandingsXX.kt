package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StandingsXX(
    @SerialName("groups")
    val groups: List<Group> = listOf()
)