package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import com.google.gson.annotations.SerializedName

data class Position(
    @SerializedName("abbreviation")
    val abbreviation: String = "",
    @SerializedName("displayName")
    val displayName: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("leaf")
    val leaf: Boolean = false,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("parent")
    val parent: Parent = Parent(),
    @SerializedName("slug")
    val slug: String = ""
)