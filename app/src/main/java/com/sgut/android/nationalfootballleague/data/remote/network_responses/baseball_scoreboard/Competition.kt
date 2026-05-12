package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Competition(
    @SerialName("attendance")
    val attendance: Int? = 0,
    @SerialName("broadcasts")
    val broadcasts: List<Broadcast>? = listOf(),
    @SerialName("competitors")
    val competitors: List<Competitor>? = listOf(),
    @SerialName("conferenceCompetition")
    val conferenceCompetition: Boolean? = false,
    @SerialName("date")
    val date: String? = "",
    @SerialName("format")
    val format: Format? = Format(),
    @SerialName("geoBroadcasts")
    val geoBroadcasts: List<GeoBroadcast>? = listOf(),
    @SerialName("headlines")
    val headlines: List<Headline>? = listOf(),
    @SerialName("id")
    val id: String? = "",
    @SerialName("leaders")
    val leaders: List<LeaderXX>? = listOf(),
    @SerialName("neutralSite")
    val neutralSite: Boolean? = false,
    @Transient
    val notes: List<Any>? = listOf(),
    @SerialName("odds")
    val odds: List<Odd>? = listOf(),
    @SerialName("outsText")
    val outsText: String? = "",
    @SerialName("playByPlayAvailable")
    val playByPlayAvailable: Boolean? = false,
    @SerialName("recent")
    val recent: Boolean? = false,
    @SerialName("situation")
    val situation: Situation? = Situation(),
    @SerialName("startDate")
    val startDate: String? = "",
    @SerialName("status")
    val status: Status? = Status(),
    @SerialName("tickets")
    val tickets: List<Ticket>? = listOf(),
    @SerialName("timeValid")
    val timeValid: Boolean? = false,
    @SerialName("type")
    val type: TypeXXX? = TypeXXX(),
    @SerialName("uid")
    val uid: String? = "",
    @SerialName("venue")
    val venue: Venue? = Venue(),
    @SerialName("wasSuspended")
    val wasSuspended: Boolean? = false
)