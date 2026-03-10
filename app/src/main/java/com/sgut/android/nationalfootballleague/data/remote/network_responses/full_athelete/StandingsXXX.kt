package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StandingsXXX(
    @SerialName("entries")
    val entries: List<Entry> = listOf(),
    @SerialName("header")
    val header: String = "",
    @SerialName("href")
    val href: String = ""
)