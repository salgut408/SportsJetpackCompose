package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.CuratedRankTennisModel
import kotlinx.serialization.Serializable

@Serializable
data class CuratedRank(
    @SerialName("current")
    val current: Int = 0
)
fun CuratedRank.asDomain():CuratedRankTennisModel{
    return CuratedRankTennisModel(
        current = current
    )
}