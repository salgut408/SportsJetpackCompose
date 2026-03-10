package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Franchise(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("location")
    val location: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("shortDisplayName")
    val shortDisplayName: String = "",
    @SerialName("slug")
    val slug: String = "",
    @SerialName("uid")
    val uid: String = ""
)