package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.*
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.VideoModel
import kotlinx.serialization.Serializable


@Serializable
data class Videos(

    @SerialName("source")
    val source: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("headline")
    val headline: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("ad")
    val ad: Ad? = Ad(),
    @SerialName("tracking")
    val tracking: Tracking? = Tracking(),
    @SerialName("cerebroId")
    val cerebroId: String? = null,
    @SerialName("lastModified")
    val lastModified: String? = null,
    @SerialName("originalPublishDate")
    val originalPublishDate: String? = null,
    @SerialName("timeRestrictions")
    val timeRestrictions: TimeRestrictions? = TimeRestrictions(),
    @SerialName("deviceRestrictions")
    val deviceRestrictions: DeviceRestrictions? = DeviceRestrictions(),
    @SerialName("duration")
    val duration: Int? = null,
    @SerialName("thumbnail")
    val thumbnail: String? = null,
    @SerialName("links")
    val links: GameDetailsLinks? = GameDetailsLinks(),

    )

fun Videos.asDomain(): VideoModel {
    return VideoModel(
        source = source ?: "",
        id = id ?: 0,
        headline = headline ?: "",
        description = description ?: "",
        lastModified = lastModified ?: "",
        originalPublishDate = originalPublishDate ?: "",
        duration = duration ?: 0,
        thumbnail = thumbnail ?: "",
        links = links ?: GameDetailsLinks()
    )
}