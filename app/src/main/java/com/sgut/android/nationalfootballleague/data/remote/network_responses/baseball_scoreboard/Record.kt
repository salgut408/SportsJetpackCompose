package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import com.google.gson.annotations.SerializedName

data class Record(
    @SerializedName("abbreviation")
    val abbreviation: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("summary")
    val summary: String? = "",
    @SerializedName("type")
    val type: String? = ""
)