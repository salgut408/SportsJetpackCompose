package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.football.nfl


import com.google.gson.annotations.SerializedName

data class Season(
    @SerializedName("slug")
    val slug: String = "",
    @SerializedName("type")
    val type: Int = 0,
    @SerializedName("year")
    val year: Int = 0
)