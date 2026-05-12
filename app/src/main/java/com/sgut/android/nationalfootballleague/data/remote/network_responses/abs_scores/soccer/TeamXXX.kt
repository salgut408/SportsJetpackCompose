package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.soccer


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamXXX(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("logo")
    val logo: String = "",
    @SerialName("uid")
    val uid: String = ""
)