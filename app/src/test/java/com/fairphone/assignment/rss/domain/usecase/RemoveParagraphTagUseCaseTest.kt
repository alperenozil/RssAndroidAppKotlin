package com.fairphone.assignment.rss.domain.usecase

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RemoveParagraphTagUseCaseTest {

    private lateinit var removeParagraphTagUseCase: RemoveParagraphTagUseCase

    @Before
    fun setUp() {
        // Initialize the UseCase class before each test
        removeParagraphTagUseCase = RemoveParagraphTagUseCase()
    }

    @Test
    fun `test invoke returns content between paragraph tags`() {
        // Given a string with paragraph tags
        val input = "<p>Hello, World!</p>"

        // When invoke is called
        val result = removeParagraphTagUseCase(input)

        // Then the content between the tags should be returned
        assertEquals("Hello, World!", result)
    }

    @Test
    fun `test invoke throws exception when no opening tag is present`() {
        // Given a string without an opening <p> tag
        val input = "Hello, World!</p>"

        // When invoke is called, it should throw an exception
        assertFailsWith<IndexOutOfBoundsException> {
            removeParagraphTagUseCase(input)
        }
    }

    @Test
    fun `test invoke throws exception when no closing tag is present`() {
        // Given a string without a closing </p> tag
        val input = "<p>Hello, World!"

        // When invoke is called, it should throw an exception
        assertFailsWith<IndexOutOfBoundsException> {
            removeParagraphTagUseCase(input)
        }
    }

    @Test
    fun `test invoke returns empty string when input is empty`() {
        // Given an empty string
        val input = ""

        // When invoke is called
        val result = removeParagraphTagUseCase(input)

        // Then it should return an empty string (empty input is handled)
        assertEquals("", result)
    }

    @Test
    fun `test invoke handles malformed input`() {
        // Given a malformed string with <p> but no </p> or vice versa
        val input = "<p>Content without closing tag"

        // When invoke is called, it should throw an exception
        assertFailsWith<IndexOutOfBoundsException> {
            removeParagraphTagUseCase(input)
        }
    }
}
