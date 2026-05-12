package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.LeagueTennisModel
import com.sgut.android.nationalfootballleague.utils.formatTo
import com.sgut.android.nationalfootballleague.utils.toDate
import kotlinx.serialization.Serializable

@Serializable
data class League(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("calendar")
    val calendar: List<String> = listOf(),
    @SerialName("calendarEndDate")
    val calendarEndDate: String = "",
    @SerialName("calendarIsWhitelist")
    val calendarIsWhitelist: Boolean = false,
    @SerialName("calendarStartDate")
    val calendarStartDate: String = "",
    @SerialName("calendarType")
    val calendarType: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("logos")
    val logos: List<Logo> = listOf(),
    @SerialName("midsizeName")
    val midsizeName: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("season")
    val season: SeasonX = SeasonX(),
    @SerialName("slug")
    val slug: String = "",
    @SerialName("uid")
    val uid: String = ""
)

fun League.asDomain(): LeagueTennisModel{
    return LeagueTennisModel(
        abbreviation = abbreviation,
        calendar = calendar.map { it.toDate()?.formatTo("K:mm aa") ?: "" },
        calendarEndDate = calendarEndDate,
        calendarIsWhitelist = calendarIsWhitelist,
        calendarStartDate = calendarStartDate,
        calendarType = calendarType,
        id = id,
        logos = logos.map { it.asDomain() },
        midsizeName = midsizeName,
        name = name,
        season = season.asDomain(),
        slug = slug,
        uid = uid
    )
}