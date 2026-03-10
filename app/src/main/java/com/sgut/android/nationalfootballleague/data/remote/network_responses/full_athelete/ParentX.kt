package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParentX(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("isConference")
    val isConference: Boolean = false,
    @SerialName("midsizeName")
    val midsizeName: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("parent")
    val parent: ParentX = ParentX(),
    @SerialName("season")
    val season: SeasonX = SeasonX(),
    @SerialName("shortName")
    val shortName: String = "",
    @SerialName("slug")
    val slug: String = "",
    @SerialName("standings")
    val standings: StandingsXX = StandingsXX(),

    @SerialName("uid")
    val uid: String = ""
)