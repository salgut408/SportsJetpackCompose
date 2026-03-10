package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.AddressModel
import kotlinx.serialization.Serializable


@Serializable
data class Address3(

  @SerialName("city")
  val city: String? = null,
  @SerialName("state")
  val state: String? = null,
  @SerialName("zipCode")
  val zipCode: String? = null,

  )

fun Address3.asDomain(): AddressModel {
    return AddressModel(
        city = city ?: "",
        state = state ?: "",
        zipCode = zipCode ?: ""
    )
}