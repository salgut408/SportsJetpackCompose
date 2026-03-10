package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.nba


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoBroadcast(
    @SerialName("lang")
    val lang: String = "",
    @SerialName("market")
    val market: Market = Market(),
    @SerialName("media")
    val media: Media = Media(),
    @SerialName("region")
    val region: String = "",
    @SerialName("type")
    val type: Type = Type()
)