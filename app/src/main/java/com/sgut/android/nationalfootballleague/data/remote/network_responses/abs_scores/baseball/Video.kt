package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.baseball


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Video(

    @SerialName("duration")
    val duration: Int = 0,

    @SerialName("headline")
    val headline: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("links")
    val links: Links = Links(),
    @SerialName("source")
    val source: String = "",
    @SerialName("thumbnail")
    val thumbnail: String = "",
    @SerialName("tracking")
    val tracking: Tracking = Tracking()
)