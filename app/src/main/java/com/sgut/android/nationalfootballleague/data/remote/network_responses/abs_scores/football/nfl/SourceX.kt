package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.football.nfl


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceX(
    @SerialName("flash")
    val flash: Flash = Flash(),
    @SerialName("full")
    val full: Full = Full(),
    @SerialName("HD")
    val hD: HD = HD(),
    @SerialName("hds")
    val hds: Hds = Hds(),
    @SerialName("href")
    val href: String = "",
    @SerialName("mezzanine")
    val mezzanine: Mezzanine = Mezzanine()
)