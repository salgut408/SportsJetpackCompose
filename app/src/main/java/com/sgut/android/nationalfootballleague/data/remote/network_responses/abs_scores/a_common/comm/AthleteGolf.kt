package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm

import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.golf.Flag
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AthleteGolf(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("flag")
    val flag: Flag = Flag(),
    @SerialName("fullName")
    val fullName: String = "",
    @SerialName("shortName")
    val shortName: String = ""
)