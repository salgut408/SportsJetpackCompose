package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Odd(
    @SerialName("awayTeamOdds")
    val awayTeamOdds: AwayTeamOdds? = AwayTeamOdds(),
    @SerialName("details")
    val details: String? = "",
    @SerialName("homeTeamOdds")
    val homeTeamOdds: HomeTeamOdds? = HomeTeamOdds(),
    @SerialName("overUnder")
    val overUnder: Double? = 0.0,
    @SerialName("provider")
    val provider: Provider? = Provider()
)