package com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.DefaultLeagueModel
import kotlinx.serialization.Serializable


@Serializable
data class LeaguesScoreboard(

    @SerialName("id")
    val id: String? = null,
    @SerialName("uid")
    val uid: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("abbreviation")
    val abbreviation: String? = null,
    @SerialName("midsizeName")
    val midsizeName: String? = null,
    @SerialName("slug")
    val slug: String? = null,
    @SerialName("logos")
    val logos: List<LogosScoreboard> = listOf(),
)



fun LeaguesScoreboard.asDomain(): DefaultLeagueModel {
    return DefaultLeagueModel(
        abbreviation = abbreviation ?: "",
        id = id ?: "",
        logos = logos.map { it.asDomain() },
        name = name ?: "",
        slug = slug ?: "",
        uid = uid ?: "",
//        calendar = calendar
    )
}