package com.fairphone.assignment.rss.domain.usecase

import com.fairphone.assignment.rss.common.Resource
import com.fairphone.assignment.rss.data.local.FeedEntity
import com.fairphone.assignment.rss.domain.repository.FeedRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import kotlin.test.Test

@ExperimentalCoroutinesApi
class GetFeedUseCaseTest {

    private lateinit var getFeedUseCase: GetFeedUseCase
    private val feedRepository: FeedRepository = mockk()

    @Before
    fun setUp() {
        getFeedUseCase = GetFeedUseCase(feedRepository)
    }

    @Test
    fun `getFeedUseCase returns a flow of success`(): Unit = runTest {
        val mockFeedList = listOf(mockk<FeedEntity>(relaxed = true), mockk<FeedEntity>(relaxed = true))
        coEvery { feedRepository.getFeed() } returns flow {
            emit(Resource.Success(mockFeedList))
        }

        val result = getFeedUseCase()

        result.collect {
            assert(it is Resource.Success)
            assert((it as Resource.Success).data == mockFeedList)
        }
    }
}
