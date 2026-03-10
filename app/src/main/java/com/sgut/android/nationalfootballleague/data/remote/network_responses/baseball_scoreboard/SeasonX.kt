package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonX(
    @SerialName("displayName")
    val displayName: String? = "",
    @SerialName("endDate")
    val endDate: String? = "",
    @SerialName("startDate")
    val startDate: String? = "",
    @SerialName("type")
    val type: TypeXXXXX? = TypeXXXXX(),
    @SerialName("year")
    val year: Int? = 0
)