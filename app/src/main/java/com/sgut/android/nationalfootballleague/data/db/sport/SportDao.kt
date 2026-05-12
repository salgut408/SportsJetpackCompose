package com.sgut.android.nationalfootballleague.data.db.sport

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sgut.android.nationalfootballleague.data.db.league.LeagueDbObj
import kotlinx.coroutines.flow.Flow

@Dao
interface SportDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteSport(sport: SportDbObj)

    @Query("SELECT * FROM sport_table")
    fun getFavoriteSports(): Flow<List<SportDbObj>>

    @Query("DELETE FROM sport_table WHERE uid = :sportUid")
    suspend fun deleteFavoriteLeague(sportUid: String)
}