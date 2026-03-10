package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.DomainLogoModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsLogos(

  @SerialName("href")
  val href: String? = null,
  @SerialName("width")
  val width: Int? = null,
  @SerialName("height")
  val height: Int? = null,
  @SerialName("alt")
  val alt: String? = null,
  @SerialName("rel")
  val rel: ArrayList<String> = arrayListOf(),
  @SerialName("lastUpdated")
  val lastUpdated: String? = null,

  )

fun GameDetailsLogos.asDomainLogo(): DomainLogoModel {
  return DomainLogoModel(
    width = width ?: 0,
    height = height ?: 0,
    href = href ?: ""
    )
}