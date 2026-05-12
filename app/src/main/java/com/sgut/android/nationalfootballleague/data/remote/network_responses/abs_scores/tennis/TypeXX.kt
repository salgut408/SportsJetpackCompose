package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.tennis


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeXX(
    @SerialName("id")
    val id: String = "",
    @SerialName("slug")
    val slug: String = "",
    @SerialName("text")
    val text: String = ""
)