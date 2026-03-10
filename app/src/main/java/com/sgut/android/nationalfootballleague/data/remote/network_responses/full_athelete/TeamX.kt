package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamX(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("alternateColor")
    val alternateColor: String = "",
    @SerialName("alternateLocation")
    val alternateLocation: String = "",
    @SerialName("color")
    val color: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("links")
    val links: List<LinkXX> = listOf(),
    @SerialName("location")
    val location: String = "",
    @SerialName("logo")
    val logo: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("shortDisplayName")
    val shortDisplayName: String = "",
    @SerialName("slug")
    val slug: String = "",
    @SerialName("uid")
    val uid: String = ""
)