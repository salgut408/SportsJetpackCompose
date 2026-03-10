package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.college


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Record(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("summary")
    val summary: String = "",
    @SerialName("type")
    val type: String = ""
)