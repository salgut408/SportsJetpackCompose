package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.racing


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class League(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("logos")
    val logos: List<Logo> = listOf(),
    @SerialName("name")
    val name: String = "",
    @SerialName("slug")
    val slug: String = "",
    @SerialName("uid")
    val uid: String = ""
)