package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.baseball

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard.Situation
import kotlinx.serialization.Serializable

@Serializable
data class Competition(
    @SerialName("competitors")
    val competitors: List<Competitor>? = listOf(),
    @SerialName("id")
    val id: String? = "",
    @SerialName("outsText")
    val outsText: String? = "",
    @SerialName("situation")
    val situation: Situation? = Situation(),
    @SerialName("startDate")
    val startDate: String? = "",
    @SerialName("status")
    val status: Status? = Status(),
    @SerialName("uid")
    val uid: String? = "",

)