package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeagueCommon(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    val id: String = "",
    @SerialName("logos")
    val logos: List<LogoComm> = listOf(),
    @SerialName("name")
    val name: String = "",
    @SerialName("slug")
    val slug: String = "",
    @SerialName("uid")
    val uid: String = ""
)