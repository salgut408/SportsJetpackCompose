package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailsFranchiseModel
import kotlinx.serialization.Serializable


@Serializable
data class Franchise3(

    @SerialName("\$ref") var ref: String? = null,
    @SerialName("id") val id: String? = null,
    @SerialName("uid") val uid: String? = null,
    @SerialName("slug") val slug: String? = null,
    @SerialName("location") val location: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("nickname") val nickname: String? = null,
    @SerialName("abbreviation") val abbreviation: String? = null,
    @SerialName("displayName") val displayName: String? = null,
    @SerialName("shortDisplayName") val shortDisplayName: String? = null,
    @SerialName("color") val color: String? = null,
    @SerialName("isActive") val isActive: Boolean? = null,
    @SerialName("venue") val venue: Venue3? = Venue3(),
)

 fun Franchise3.asDomain() : FullTeamDetailsFranchiseModel {
     return FullTeamDetailsFranchiseModel(
         id = id ?: "",
         uid = uid ?: "",
         slug = slug ?: "",
         location = location ?: "",
         name = name ?: "",
         abbreviation = abbreviation ?: "",
         displayName = displayName ?: "",
         shortDisplayName = shortDisplayName ?: "",
         color = color ?: "",
         isActive = isActive ?: true,
         venue = venue?.asDomain()


     )
 }