package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.football.nfl


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Team(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("alternateColor")
    val alternateColor: String = "",
    @SerialName("color")
    val color: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("isActive")
    val isActive: Boolean = false,
    @SerialName("links")
    val links: List<Link> = listOf(),
    @SerialName("location")
    val location: String = "",
    @SerialName("logo")
    val logo: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("shortDisplayName")
    val shortDisplayName: String = "",
    @SerialName("uid")
    val uid: String = "",
    @SerialName("venue")
    val venue: Venue = Venue()
)