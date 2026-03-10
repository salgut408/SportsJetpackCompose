package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LastPlay(

    @SerialName("alternativeType")
    val alternativeType: AlternativeType? = AlternativeType(),
    @SerialName("atBatId")
    val atBatId: String? = "",
    @SerialName("athletesInvolved")
    val athletesInvolved: List<AthletesInvolved>? = listOf(),
    @SerialName("id")
    val id: String? = "",
    @SerialName("probability")
    val probability: Probability? = Probability(),
    @SerialName("scoreValue")
    val scoreValue: Int? = 0,
    @SerialName("summaryType")
    val summaryType: String? = "",
    @SerialName("team")
    val team: TeamX? = TeamX(),
    @SerialName("text")
    val text: String? = "",
    @SerialName("type")
    val type: TypeX? = TypeX()
)