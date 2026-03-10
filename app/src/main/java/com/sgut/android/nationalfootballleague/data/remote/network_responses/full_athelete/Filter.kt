package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Filter(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("options")
    val options: List<Option> = listOf(),
    @SerialName("value")
    val value: String = ""
)