package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PositionX(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("leaf")
    val leaf: Boolean = false,
    @SerialName("name")
    val name: String = "",
    @SerialName("parent")
    val parent: ParentX = ParentX(),
    @SerialName("slug")
    val slug: String = ""
)