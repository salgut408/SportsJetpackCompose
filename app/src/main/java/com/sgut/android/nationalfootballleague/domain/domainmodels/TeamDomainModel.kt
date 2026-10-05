package com.sgut.android.nationalfootballleague.domain.domainmodels

import com.google.firebase.firestore.DocumentId
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.Logos
data class TeamDomainModel(
    @DocumentId
    val id: String = "",
    val uid: String = "",
    val slug: String = "",
    val abbreviation: String = "",
    val displayName: String = "",
    val shortDisplayName: String = "",
    val name: String = "",
    val nickname: String = "",
    val location: String = "",
    val color: String = "",
    val alternateColor: String = "",
    val logos: List<Logos>? = listOf(),
)


