package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.baseball


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tracking(
    @SerialName("coverageType")
    val coverageType: String = "",
    @SerialName("leagueName")
    val leagueName: String = "",
    @SerialName("sportName")
    val sportName: String = "",
    @SerialName("trackingId")
    val trackingId: String = "",
    @SerialName("trackingName")
    val trackingName: String = ""
)