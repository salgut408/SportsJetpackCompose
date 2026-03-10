package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Position(
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
    val parent: Parent = Parent(),
    @SerialName("slug")
    val slug: String = ""
)