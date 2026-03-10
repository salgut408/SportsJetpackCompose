package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.football.nfl


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeX(
    @SerialName("completed")
    val completed: Boolean = false,
    @SerialName("description")
    val description: String = "",
    @SerialName("detail")
    val detail: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("shortDetail")
    val shortDetail: String = "",
    @SerialName("state")
    val state: String = ""
)