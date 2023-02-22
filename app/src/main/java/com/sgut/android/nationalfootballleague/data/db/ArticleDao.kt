package com.sgut.android.nationalfootballleague.data.db

import androidx.room.*

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article_table")
    suspend fun getAllSavedArticles(): List<ArticleDbObject>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleDbObject)

    @Delete
    suspend fun delete(article: ArticleDbObject)



}