package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypesX(
    @SerialName("count")
    val count: Int = 0,
    @SerialName("items")
    val items: List<ItemXX> = listOf(),
    @SerialName("pageCount")
    val pageCount: Int = 0,
    @SerialName("pageIndex")
    val pageIndex: Int = 0,
    @SerialName("pageSize")
    val pageSize: Int = 0
)