package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.football.nfl


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    @SerialName("api")
    val api: Api = Api(),
    @SerialName("mobile")
    val mobile: Mobile = Mobile(),
    @SerialName("source")
    val source: SourceX = SourceX(),
    @SerialName("web")
    val web: Web = Web()
)