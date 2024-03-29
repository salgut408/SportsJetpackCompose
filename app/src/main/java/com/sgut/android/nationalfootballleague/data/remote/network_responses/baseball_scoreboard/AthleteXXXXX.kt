package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import com.google.gson.annotations.SerializedName

data class AthleteXXXXX(
    @SerializedName("displayName")
    val displayName: String? = "",
    @SerializedName("fullName")
    val fullName: String? = "",
    @SerializedName("headshot")
    val headshot: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("jersey")
    val jersey: String? = "",
    @SerializedName("position")
    val position: String? = "",
    @SerializedName("shortName")
    val shortName: String? = "",
    @SerializedName("team")
    val team: TeamX? = TeamX()
)