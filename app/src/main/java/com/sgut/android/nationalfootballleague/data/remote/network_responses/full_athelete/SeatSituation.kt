package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeatSituation(
    @SerialName("currentTeamName")
    val currentTeamName: String = "",
    @SerialName("date")
    val date: String = "",
    @SerialName("dateDay")
    val dateDay: String = "",
    @SerialName("dateShort")
    val dateShort: String = "",
    @SerialName("eventLink")
    val eventLink: String = "",
    @SerialName("genericLink")
    val genericLink: String = "",
    @SerialName("homeAway")
    val homeAway: String = "",
    @SerialName("neutralSite")
    val neutralSite: Boolean = false,
    @SerialName("opponentTeamName")
    val opponentTeamName: String = "",
    @SerialName("summary")
    val summary: String = "",
    @SerialName("teamLink")
    val teamLink: String = "",
    @SerialName("venueLink")
    val venueLink: String = "",
    @SerialName("venueName")
    val venueName: String = ""
)