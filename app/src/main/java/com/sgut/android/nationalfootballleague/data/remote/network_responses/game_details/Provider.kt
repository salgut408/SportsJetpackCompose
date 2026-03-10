package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.ProviderModel
import kotlinx.serialization.Serializable


@Serializable
data class Provider(

  @SerialName("id")
  val id: String? = null,
  @SerialName("name")
  val name: String? = null,
  @SerialName("priority")
  val priority: Int? = null,

  )

fun Provider.asDomain(): ProviderModel {
  return ProviderModel(
    id = id ?: "",
    name = name ?: "",
    priority = priority ?: 0
  )
}