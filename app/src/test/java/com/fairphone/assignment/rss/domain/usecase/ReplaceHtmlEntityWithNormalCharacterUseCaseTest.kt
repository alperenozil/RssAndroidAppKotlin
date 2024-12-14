package com.fairphone.assignment.rss.domain.usecase

import com.fairphone.assignment.rss.domain.usecase.ReplaceHtmlEntityWithNormalCharacterUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class ReplaceHtmlEntityWithNormalCharacterUseCaseTest {

    private val useCase = ReplaceHtmlEntityWithNormalCharacterUseCase()

    @Test
    fun `invoke replaces HTML entities with normal characters`() {
        val input = "This is a test with &#8220;quotes&#8221; and &#8211;a dash&#8211;."
        val expectedOutput = "This is a test with “quotes” and ’a dash’."

        val actualOutput = useCase(input)

        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun `invoke handles empty input`() {
        val input = ""
        val expectedOutput = ""

        val actualOutput = useCase(input)

        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun `invoke handles input with no HTML entities`() {
        val input = "This is a test with no HTML entities."
        val expectedOutput = "This is a test with no HTML entities."

        val actualOutput = useCase(input)

        assertEquals(expectedOutput, actualOutput)
    }
}