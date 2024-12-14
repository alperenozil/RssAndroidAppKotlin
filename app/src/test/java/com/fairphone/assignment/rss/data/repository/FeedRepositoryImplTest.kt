package com.fairphone.assignment.rss.data.repository

import com.fairphone.assignment.rss.data.FairphoneApi
import com.fairphone.assignment.rss.data.local.FeedDao
import com.fairphone.assignment.rss.data.remote.FeedDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class FeedRepositoryImplTest {

    private val feedApi = mockk<FairphoneApi>()
    private val feedDao = mockk<FeedDao>()
    private val repository = FeedRepositoryImpl(feedApi, feedDao)

    @Test
    fun `getFeed should fetch data from remote and cache it locally`() = runTest {
        // Arrange
        val remoteFeed = mockk<FeedDto>(relaxed = true)
        coEvery { feedApi.getFeed() } returns remoteFeed
        coEvery { feedDao.insert(any()) } returns Unit

        // Act
        val result = repository.getFeed().first()

        // Assert
        assertEquals(remoteFeed, result)
        coVerify(exactly = 1) { feedApi.getFeed() }
        coVerify(exactly = 1) { feedDao.insert(any()) }
    }
}