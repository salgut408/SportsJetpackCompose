package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Group(
    @SerialName("groups")
    val groups: List<GroupX> = listOf(),
    @SerialName("header")
    val header: String = ""
)