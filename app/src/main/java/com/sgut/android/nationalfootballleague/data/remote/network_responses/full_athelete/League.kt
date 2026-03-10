package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class League(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("logos")
    val logos: List<Logo> = listOf(),
    @SerialName("midsizeName")
    val midsizeName: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("shortName")
    val shortName: String = "",
    @SerialName("slug")
    val slug: String = "",
    @SerialName("uid")
    val uid: String = ""
)