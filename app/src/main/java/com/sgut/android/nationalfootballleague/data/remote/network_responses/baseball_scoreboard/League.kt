package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class League(
    @SerialName("abbreviation")
    val abbreviation: String? = "",
//    TODO FIX THIS
//    @SerialName("calendar")
//    val calendar: List<String>? = listOf(),
    @SerialName("calendarEndDate")
    val calendarEndDate: String? = "",
    @SerialName("calendarIsWhitelist")
    val calendarIsWhitelist: Boolean? = false,
    @SerialName("calendarStartDate")
    val calendarStartDate: String? = "",
    @SerialName("calendarType")
    val calendarType: String? = "",
    @SerialName("id")
    val id: String? = "",
    @SerialName("logos")
    val logos: List<Logo>? = listOf(),
    @SerialName("midsizeName")
    val midsizeName: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("season")
    val season: SeasonX? = SeasonX(),
    @SerialName("slug")
    val slug: String? = "",
    @SerialName("uid")
    val uid: String? = ""
)