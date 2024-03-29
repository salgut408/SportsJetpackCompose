package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import com.google.gson.annotations.SerializedName

data class TypeXXXXX(
    @SerializedName("abbreviation")
    val abbreviation: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("type")
    val type: Int? = 0
)