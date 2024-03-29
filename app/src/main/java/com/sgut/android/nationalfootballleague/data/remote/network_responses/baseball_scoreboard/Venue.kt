package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import com.google.gson.annotations.SerializedName

data class Venue(
    @SerializedName("address")
    val address: Address? = Address(),
    @SerializedName("capacity")
    val capacity: Int? = 0,
    @SerializedName("fullName")
    val fullName: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("indoor")
    val indoor: Boolean? = false
)