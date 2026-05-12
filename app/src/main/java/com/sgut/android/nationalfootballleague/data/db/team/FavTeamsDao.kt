package com.sgut.android.nationalfootballleague.data.db.team

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sgut.android.nationalfootballleague.data.db.league.LeagueDbObj
import kotlinx.coroutines.flow.Flow

@Dao
interface FavTeamsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteTeam(team: TeamDbObj)

    @Query("SELECT * FROM fave_team_table")
    fun getFavoriteTeams(): Flow<List<TeamDbObj>>

    @Query("DELETE FROM fave_team_table WHERE uid = :teamUid")
    suspend fun deleteFavoriteTeam(teamUid: String)
}