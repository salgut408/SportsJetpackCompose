package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Entry(
    @SerialName("id")
    val id: String = "",
    @SerialName("link")
    val link: String = "",
    @SerialName("logo")
    val logo: Logo = Logo(),
    @SerialName("stats")
    val stats: List<Stat> = listOf(),
    @SerialName("team")
    val team: String = "",
    @SerialName("uid")
    val uid: String = ""
)