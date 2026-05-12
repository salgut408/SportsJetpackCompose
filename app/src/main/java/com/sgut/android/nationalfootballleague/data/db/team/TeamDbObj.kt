package com.sgut.android.nationalfootballleague.data.db.team

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fave_team_table")
data class TeamDbObj(
    val id: String = "",
    @PrimaryKey(autoGenerate = false)
    val uid: String = "",
    val slug: String = "",
    val abbreviation: String = "",
    val displayName: String = "",
    val color: String = "",
    val alternateColor: String = "",
    var isFavorite: Boolean = false,
    val logo: String = "",
    val sport: String = "",
    val league: String = ""
    )
