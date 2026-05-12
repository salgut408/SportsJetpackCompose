package com.sgut.android.nationalfootballleague.data.db.league

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LeagueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteLeague(league: LeagueDbObj)

    @Query("SELECT * FROM league_table")
    fun getFavoriteLeagues(): Flow<List<LeagueDbObj>>

    @Query("DELETE FROM league_table WHERE uid = :leagueUid")
    suspend fun deleteFavoriteLeague(leagueUid: String)

}