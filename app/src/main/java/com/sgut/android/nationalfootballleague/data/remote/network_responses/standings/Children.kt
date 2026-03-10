package com.sgut.android.nationalfootballleague.data.remote.network_responses.standings


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.standings_models.ChildrenModel
import kotlinx.serialization.Serializable

@Serializable
data class Children(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("shortName")
    val shortName: String = "",
    @SerialName("standings")
    val standings: Standings = Standings(),
    @SerialName("uid")
    val uid: String = ""
)

fun Children.asDomain(): ChildrenModel {
    return ChildrenModel(
        abbreviation = abbreviation,
        id = id,
        name = name,
        shortName = shortName,
        standings = standings.asDomain(),
        uid = uid,
    )
}