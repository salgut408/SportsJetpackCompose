package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamXXXXXX(
    @SerialName("abbreviation")
    val abbreviation: String? = "",
    @SerialName("displayName")
    val displayName: String? = "",
    @SerialName("id")
    val id: String? = "",
    @SerialName("logo")
    val logo: String? = "",
    @SerialName("uid")
    val uid: String? = ""
)