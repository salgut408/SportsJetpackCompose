package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.mma


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeXXXX(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("type")
    val type: Int = 0
)