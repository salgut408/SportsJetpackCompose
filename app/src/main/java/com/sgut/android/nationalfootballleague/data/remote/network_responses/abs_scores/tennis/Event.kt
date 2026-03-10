package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.tennis


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm.Grouping
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    @SerialName("groupings")
    val groupings: List<Grouping> = listOf(),
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("shortName")
    val shortName: String = "",
    @SerialName("uid")
    val uid: String = "",
)