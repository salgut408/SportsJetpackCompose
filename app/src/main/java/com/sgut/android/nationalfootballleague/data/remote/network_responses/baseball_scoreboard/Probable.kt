package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete.Statistic
import kotlinx.serialization.Serializable

@Serializable
data class Probable(
    @SerialName("abbreviation")
    val abbreviation: String? = "",
    @SerialName("athlete")
    val athlete: AthleteX? = AthleteX(),
    @SerialName("displayName")
    val displayName: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("playerId")
    val playerId: Int? = 0,
    @SerialName("shortDisplayName")
    val shortDisplayName: String? = "",
    @SerialName("statistics")
    val statistics: List<Statistic>? = listOf()
)