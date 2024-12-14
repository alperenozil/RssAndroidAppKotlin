package com.fairphone.assignment.rss.presentation.feed

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fairphone.assignment.rss.common.Resource
import com.fairphone.assignment.rss.data.local.FeedEntity
import com.fairphone.assignment.rss.domain.usecase.GetFeedUseCase
import com.fairphone.assignment.rss.domain.usecase.RemoveParagraphTagUseCase
import com.fairphone.assignment.rss.domain.usecase.ReplaceHtmlEntityWithNormalCharacterUseCase
import com.fairphone.assignment.rss.domain.usecase.UpdateDatabaseEntryUseCase
import com.fairphone.assignment.rss.ui.FeedViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FeedViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getFeedUseCase = mockk<GetFeedUseCase>()
    private val markAsReadUseCase = mockk<UpdateDatabaseEntryUseCase>()
    private val replaceHtmlEntityWithNormalCharacterUseCase = mockk<ReplaceHtmlEntityWithNormalCharacterUseCase>()
    private val removeParagraphTagUseCase = mockk<RemoveParagraphTagUseCase>()
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: FeedViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = FeedViewModel(
            getFeedUseCase, markAsReadUseCase,
            replaceHtmlEntityWithNormalCharacterUseCase = replaceHtmlEntityWithNormalCharacterUseCase,
            removeParagraphTagUseCase = removeParagraphTagUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getFeed should update state with success`() = runTest {
        // Arrange
        val feedList = listOf(mockk<FeedEntity>(relaxed = true), mockk<FeedEntity>(relaxed = true))
        coEvery { getFeedUseCase() } returns flowOf(Resource.Success(feedList))

        // Act
        viewModel.getFeed()

        // Assert
        assertEquals(Resource.Success(feedList), viewModel.feedState.value)
    }

    // Add more test cases for other ViewModel functions
}