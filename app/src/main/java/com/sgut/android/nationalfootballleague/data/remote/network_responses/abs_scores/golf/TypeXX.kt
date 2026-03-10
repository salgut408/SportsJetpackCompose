package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.golf


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeXX(
    @SerialName("completed")
    val completed: Boolean = false,
    @SerialName("description")
    val description: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("state")
    val state: String = ""
)