package com.fairphone.assignment.rss.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FeedDao {
    @Insert
    fun insert(feed: FeedEntity)

    @Update
    fun update(feed: FeedEntity)

    @Query("SELECT * FROM feed")
    fun getFeed(): List<FeedEntity>
}