package com.sgut.android.nationalfootballleague.data.db.article

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleDbObject)

    @Query("SELECT * FROM article_table")
     fun getAllSavedArticles(): Flow<List<ArticleDbObject>>

    @Delete
    suspend fun delete(article: ArticleDbObject)

}