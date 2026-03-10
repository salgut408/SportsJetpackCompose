package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.racing


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm.Circuit
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    @SerialName("circuit")
    val circuit: Circuit = Circuit(),
    @SerialName("competitions")
    val competitions: List<Competition> = listOf(),
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("shortName")
    val shortName: String = "",
    @SerialName("uid")
    val uid: String = ""
)