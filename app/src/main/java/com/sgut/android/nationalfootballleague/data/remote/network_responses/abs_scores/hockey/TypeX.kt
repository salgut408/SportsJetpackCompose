package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.hockey


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeX(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("id")
    val id: String = ""
)