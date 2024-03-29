package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import com.google.gson.annotations.SerializedName
import com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete.Statistic

data class Probable(
    @SerializedName("abbreviation")
    val abbreviation: String? = "",
    @SerializedName("athlete")
    val athlete: AthleteX? = AthleteX(),
    @SerializedName("displayName")
    val displayName: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("playerId")
    val playerId: Int? = 0,
    @SerializedName("shortDisplayName")
    val shortDisplayName: String? = "",
    @SerializedName("statistics")
    val statistics: List<Statistic>? = listOf()
)