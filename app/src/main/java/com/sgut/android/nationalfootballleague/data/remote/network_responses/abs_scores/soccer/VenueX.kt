package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.soccer


import com.google.gson.annotations.SerializedName

data class VenueX(
    @SerializedName("address")
    val address: Address = Address(),
    @SerializedName("fullName")
    val fullName: String = "",
    @SerializedName("id")
    val id: String = ""
)