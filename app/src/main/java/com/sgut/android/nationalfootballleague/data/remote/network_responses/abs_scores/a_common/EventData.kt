package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common

import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm.Circuit
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm.Grouping
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

abstract class EventData {
    abstract val competitions: List<CompetitionData>?
    abstract val id: String
    abstract val name: String
    abstract val shortName: String
    abstract val uid: String
}

@Serializable
data class DefaultEvent(
    @SerialName("competitions")
    override val competitions: List<DefaultCompetition>? = listOf(),
    @SerialName("id")
    override val id: String = "",
    @SerialName("name")
    override val name: String = "",
    @SerialName("shortName")
    override val shortName: String = "",
    @SerialName("uid")
    override val uid: String = ""
) : EventData()

@Serializable
data class BaseballEvent(
    @SerialName("competitions")
    override val competitions: List<BaseballCompetition>? = listOf(),
    @SerialName("id")
    override val id: String = "",
    @SerialName("name")
    override val name: String = "",
    @SerialName("shortName")
    override val shortName: String = "",
    @SerialName("uid")
    override val uid: String = ""
) : EventData()

@Serializable
data class GolfEvent(
    @SerialName("competitions")
    override val competitions: List<GolfCompetition>? = listOf(),
    @SerialName("id")
    override val id: String = "",
    @SerialName("name")
    override val name: String = "",
    @SerialName("shortName")
    override val shortName: String = "",
    @SerialName("uid")
    override val uid: String = ""
) : EventData()

@Serializable
data class MmaEvent(
    @SerialName("competitions")
    override val competitions: List<MmaCompetition>? = listOf(),
    @SerialName("id")
    override val id: String = "",
    @SerialName("name")
    override val name: String = "",
    @SerialName("shortName")
    override val shortName: String = "",
    @SerialName("uid")
    override val uid: String = ""
) : EventData()

@Serializable
data class SoccerEvent(
    @SerialName("competitions")
    override val competitions: List<SoccerCompetition>? = listOf(),
    @SerialName("id")
    override val id: String = "",
    @SerialName("name")
    override val name: String = "",
    @SerialName("shortName")
    override val shortName: String = "",
    @SerialName("uid")
    override val uid: String = ""
) : EventData()

@Serializable
data class RacingEvent(
    @SerialName("circuit")
    val circuit: Circuit = Circuit(),
    @SerialName("competitions")
    override val competitions: List<DefaultCompetition>? = listOf(),
    @SerialName("id")
    override val id: String = "",
    @SerialName("name")
    override val name: String = "",
    @SerialName("shortName")
    override val shortName: String = "",
    @SerialName("uid")
    override val uid: String = ""
) : EventData()

@Serializable
data class TennisEvent(
    @SerialName("groupings")
    val groupings: List<Grouping> = listOf(),
    // Tennis uses groupings, not competitions — field kept for interface compatibility
    @Transient
    override val competitions: List<CompetitionData>? = null,
    @SerialName("id")
    override val id: String = "",
    @SerialName("name")
    override val name: String = "",
    @SerialName("shortName")
    override val shortName: String = "",
    @SerialName("uid")
    override val uid: String = ""
) : EventData()