package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.TennisEventModel
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    @SerialName("date")
    val date: String = "",
    @SerialName("endDate")
    val endDate: String = "",
    @SerialName("groupings")
    val groupings: List<Grouping> = listOf(),
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("previousWinners")
    val previousWinners: List<PreviousWinner> = listOf(),
    @SerialName("season")
    val season: SeasonXX = SeasonXX(),
    @SerialName("shortName")
    val shortName: String = "",
    @SerialName("status")
    val status: StatusX = StatusX(),
    @SerialName("uid")
    val uid: String = "",
    @SerialName("venue")
    val venue: VenueX = VenueX()
)

fun Event.asDomain(): TennisEventModel {
    return TennisEventModel(
        date = date,
        endDate = endDate,
        groupings = groupings.map { it.asDomain() },
        id = id,
        previousWinners = previousWinners.map { it.asDomain() },
        season = season.asDomain(),
        shortName = shortName,
        status = status.asDomain(),
        uid = uid,
        venue = venue.asDomain()
    )
}