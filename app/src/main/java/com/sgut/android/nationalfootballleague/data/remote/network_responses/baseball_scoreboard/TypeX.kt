package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeX(
    @SerialName("abbreviation")
    val abbreviation: String? = "",
    @SerialName("alternativeText")
    val alternativeText: String? = "",
    @SerialName("id")
    val id: String? = "",
    @SerialName("text")
    val text: String? = "",
    @SerialName("type")
    val type: String? = ""
)