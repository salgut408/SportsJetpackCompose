package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsImages(

    @SerialName("name")
    val name: String? = null,
    @SerialName("width")
    val width: Int? = null,
    @SerialName("alt")
    val alt: String? = null,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("height")
    val height: Int? = null,
    @SerialName("href")
    val href: String? = null,


    )