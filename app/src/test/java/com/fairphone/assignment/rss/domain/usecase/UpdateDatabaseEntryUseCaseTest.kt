package com.fairphone.assignment.rss.domain.usecase

import com.fairphone.assignment.rss.data.local.FeedEntity
import com.fairphone.assignment.rss.domain.repository.FeedRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UpdateDatabaseEntryUseCaseTest {

    private val feedRepository = mockk<FeedRepository>()
    private val updateDatabaseEntryUseCase = UpdateDatabaseEntryUseCase(feedRepository)

    @Test
    fun `invoke should update database entry`() = runTest {
        // Arrange
        val feedEntity = FeedEntity(
            id = 1,
            creator = "Creator",
            pubDate = "Pub Date",
            title = "Title",
            description = "Description",
            link = "Link",
            isRead = false
        )
        coEvery { feedRepository.updateDatabaseEntry(feedEntity) } returns Unit

        // Act
        updateDatabaseEntryUseCase(feedEntity)

        // Assert
        coVerify(exactly = 1) { feedRepository.updateDatabaseEntry(feedEntity) }
    }
}