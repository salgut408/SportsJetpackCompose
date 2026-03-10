package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Quicklink(
    @SerialName("items")
    val items: List<ItemX> = listOf(),
    @SerialName("text")
    val text: String = "",
    @SerialName("title")
    val title: String = ""
)