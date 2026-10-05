package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Video(

  @SerialName("source")
  val source: String = "",
  @SerialName("id")
  val id: Int? = null,
  @SerialName("guid")
  val guid: String? = null,
  @SerialName("headline")
  val headline: String? = null,
  @SerialName("caption")
  val caption: String? = null,
  @SerialName("description")
  val description: String? = null,


  @SerialName("duration")
  val duration: Int? = null,
  @SerialName("posterImages")
  val posterImages: PosterImages? = PosterImages(),
  @SerialName("images")
  val images: List<GameDetailsImages> = listOf(),
  @SerialName("thumbnail")
  val thumbnail: String? = null,
  @SerialName("links")
  val links: GameDetailsLinks? = GameDetailsLinks(),
  @SerialName("title")
  val title: String? = null,

  )