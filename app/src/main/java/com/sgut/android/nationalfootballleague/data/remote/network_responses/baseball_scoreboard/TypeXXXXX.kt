package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeXXXXX(
    @SerialName("abbreviation")
    val abbreviation: String? = "",
    @SerialName("id")
    val id: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("type")
    val type: Int? = 0
)