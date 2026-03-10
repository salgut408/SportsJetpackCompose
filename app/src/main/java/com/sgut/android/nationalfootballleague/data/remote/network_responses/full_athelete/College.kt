package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class College(
    @SerialName("abbrev")
    val abbrev: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("mascot")
    val mascot: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("shortName")
    val shortName: String = ""
)