package com.sgut.android.nationalfootballleague.data.db.sport

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sport_table")
data class SportDbObj(
    val id: String = "",
    @PrimaryKey(autoGenerate = false)
    val uid: String = "",
    val name: String = "",
    val slug: String = ""
)
