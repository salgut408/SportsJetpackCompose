package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.TicketsModel
import kotlinx.serialization.Serializable


@Serializable
data class Tickets3(

  @SerialName("id")
  val id: String? = null,
  @SerialName("summary")
  val summary: String? = null,
  @SerialName("description")
  val description: String? = null,
  @SerialName("maxPrice")
  val maxPrice: Double? = null,
  @SerialName("startingPrice")
  val startingPrice: Double? = null,
  @SerialName("numberAvailable")
  val numberAvailable: Int? = null,
  @SerialName("totalPostings")
  val totalPostings: Int? = null,
  @SerialName("links")
  val links: List<Links3> = listOf(),

  )

fun Tickets3.asDomain() : TicketsModel {
  return  TicketsModel(
    id = id ?: "",
    maxPrice = maxPrice ?: 0.0,
    description = description ?: "",
    startingPrice = startingPrice ?: 0.0,
    numberAvailable = numberAvailable ?: 0,
    links = links
  )
}
