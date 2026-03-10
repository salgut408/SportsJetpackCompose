package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


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
    @SerialName("franchise")
    val franchise: Franchise = Franchise(),
    @SerialName("groups")
    val groups: Groups = Groups(),
    @SerialName("guid")
    val guid: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("isActive")
    val isActive: Boolean = false,
    @SerialName("isAllStar")
    val isAllStar: Boolean = false,
    @SerialName("links")
    val links: List<LinkXX> = listOf(),
    @SerialName("location")
    val location: String = "",
    @SerialName("logos")
    val logos: List<Logo> = listOf(),
    @SerialName("name")
    val name: String = "",


    @SerialName("shortDisplayName")
    val shortDisplayName: String = "",
    @SerialName("slug")
    val slug: String = "",
    @SerialName("uid")
    val uid: String = ""
)