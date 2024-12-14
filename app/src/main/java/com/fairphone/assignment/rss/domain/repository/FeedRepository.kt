package com.fairphone.assignment.rss.domain.repository

import com.fairphone.assignment.rss.common.Resource
import com.fairphone.assignment.rss.data.local.FeedEntity
import com.fairphone.assignment.rss.data.remote.FeedDto
import kotlinx.coroutines.flow.Flow

interface FeedRepository {
    suspend fun getFeed(): Flow<Resource<List<FeedEntity>>>
    suspend fun updateDatabaseEntry(entry: FeedEntity)
}