package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.golf


import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("shortName")
    val shortName: String = ""
)