package com.fairphone.assignment.rss.domain.usecase

import com.fairphone.assignment.rss.common.Resource
import com.fairphone.assignment.rss.data.local.FeedEntity
import com.fairphone.assignment.rss.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(
    private val repo: FeedRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<FeedEntity>>> = repo.getFeed()
}