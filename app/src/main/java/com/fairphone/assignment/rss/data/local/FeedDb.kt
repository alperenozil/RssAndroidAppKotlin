package com.fairphone.assignment.rss.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FeedEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun feedDao(): FeedDao
}