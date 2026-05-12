package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Week(
    @SerialName("endDate")
    val endDate: String = "",
    @SerialName("number")
    val number: Int = 0,
    @SerialName("startDate")
    val startDate: String = "",
    @SerialName("text")
    val text: String = ""
)