package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Athlete(
    @SerialName("active")
    val active: Boolean = false,
    @SerialName("age")
    val age: Int = 0,
    @SerialName("college")
    val college: College = College(),
    @SerialName("debutYear")
    val debutYear: Int = 0,
    @SerialName("displayBatsThrows")
    val displayBatsThrows: String = "",
    @SerialName("displayBirthPlace")
    val displayBirthPlace: String = "",
    @SerialName("displayDOB")
    val displayDOB: String = "",
    @SerialName("displayDraft")
    val displayDraft: String = "",
    @SerialName("displayExperience")
    val displayExperience: String = "",
    @SerialName("displayHeight")
    val displayHeight: String = "",
    @SerialName("displayJersey")
    val displayJersey: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("displayWeight")
    val displayWeight: String = "",
    @SerialName("firstName")
    val firstName: String = "",
    @SerialName("fullName")
    val fullName: String = "",
    @SerialName("guid")
    val guid: String = "",
    @SerialName("headshot")
    val headshot: Headshot = Headshot(),
    @SerialName("id")
    val id: String = "",
    @SerialName("jersey")
    val jersey: String = "",
    @SerialName("lastName")
    val lastName: String = "",
    @SerialName("position")
    val position: Position = Position(),
    @SerialName("statsSummary")
    val statsSummary: StatsSummary = StatsSummary(),
    @SerialName("status")
    val status: Status = Status(),
    @SerialName("team")
    val team: Team = Team(),
    @SerialName("type")
    val type: String = "",
    @SerialName("uid")
    val uid: String = ""
)