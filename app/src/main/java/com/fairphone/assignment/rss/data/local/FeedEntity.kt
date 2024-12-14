package com.fairphone.assignment.rss.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feed")
data class FeedEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val creator: String,
    val pubDate: String,
    var title: String,
    var description: String,
    val link: String,
    val isRead: Boolean = false
)