package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamXXX(
    @SerialName("abbreviation")
    val abbreviation: String? = "",
    @SerialName("alternateColor")
    val alternateColor: String? = "",
    @SerialName("color")
    val color: String? = "",
    @SerialName("displayName")
    val displayName: String? = "",
    @SerialName("id")
    val id: String? = "",
    @SerialName("isActive")
    val isActive: Boolean? = false,
    @SerialName("links")
    val links: List<LinkXX>? = listOf(),
    @SerialName("location")
    val location: String? = "",
    @SerialName("logo")
    val logo: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("shortDisplayName")
    val shortDisplayName: String? = "",
    @SerialName("uid")
    val uid: String? = ""
)