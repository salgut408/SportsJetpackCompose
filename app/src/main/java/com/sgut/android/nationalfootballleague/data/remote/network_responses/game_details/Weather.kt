package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.WeatherModel
import kotlinx.serialization.Serializable


@Serializable
data class Weather(

  @SerialName("temperature")
  val temperature: String = "",
  @SerialName("highTemperature")
  val highTemperature: Int = 0,
  @SerialName("lowTemperature")
  val lowTemperature: Int = 0,
  @SerialName("conditionId")
  val conditionId: String = "",
  @SerialName("gust")
  val gust: Int = 0,
  @SerialName("precipitation")
  val precipitation: Int = 0,
  @SerialName("link")
  val link: GameDetailsLink? = GameDetailsLink(),

  )
fun Weather.asDomain(): WeatherModel {
  return WeatherModel(
    temperature = temperature,
    highTemperature = highTemperature,
    lowTemperature = lowTemperature,
    conditionId = conditionId ?: "",
    gust = gust,
    precipitation = precipitation ?: 0
  )
}