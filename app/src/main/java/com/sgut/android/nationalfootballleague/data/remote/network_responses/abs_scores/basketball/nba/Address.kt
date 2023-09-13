package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.nba


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city")
    val city: String = "",
    @SerializedName("state")
    val state: String = ""
)