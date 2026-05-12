package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.football.college


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Groups(
    @SerialName("id")
    val id: String = "",
    @SerialName("isConference")
    val isConference: Boolean = false,
    @SerialName("name")
    val name: String = "",
    @SerialName("shortName")
    val shortName: String = ""
)