package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.baseball


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Mobile(
    @SerialName("alert")
    val alert: Alert = Alert(),
    @SerialName("href")
    val href: String = "",
    @SerialName("progressiveDownload")
    val progressiveDownload: ProgressiveDownload = ProgressiveDownload(),
    @SerialName("source")
    val source: Source = Source(),
    @SerialName("streaming")
    val streaming: Streaming = Streaming()
)