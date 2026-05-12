package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkAthleteResponse(
    @SerialName("athlete")
    val athlete: Athlete = Athlete(),
    @SerialName("league")
    val league: League = League(),
    @SerialName("playerSwitcher")
    val playerSwitcher: PlayerSwitcher = PlayerSwitcher(),
    @SerialName("quicklinks")
    val quicklinks: List<Quicklink> = listOf(),
    @SerialName("season")
    val season: SeasonXX = SeasonXX(),
    @SerialName("standings")
    val standings: StandingsXX = StandingsXX(),
    @SerialName("ticketsInfo")
    val ticketsInfo: TicketsInfo = TicketsInfo()
)