package com.sgut.android.nationalfootballleague.data.db.league

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "league_table")
data class LeagueDbObj(
    val id: String = "",
    @PrimaryKey(autoGenerate = false)
    val uid: String = "",
    val name: String = "",
    val abbreviation: String = "",
    val shortName: String = "",
    val slug: String = "",
)
