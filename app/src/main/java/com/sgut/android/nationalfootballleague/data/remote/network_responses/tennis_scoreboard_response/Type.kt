package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.TypeTennisModel
import kotlinx.serialization.Serializable

@Serializable
data class Type(
    @SerialName("completed")
    val completed: Boolean = false,
    @SerialName("description")
    val description: String = "",
    @SerialName("detail")
    val detail: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("shortDetail")
    val shortDetail: String = "",
    @SerialName("state")
    val state: String = ""
)

fun Type.asDomain(): TypeTennisModel{
    return TypeTennisModel(
        completed = completed,
        description = description,
        detail = detail,
        id = id,
        name = name,
        shortDetail = shortDetail,
        state = state
    )
}