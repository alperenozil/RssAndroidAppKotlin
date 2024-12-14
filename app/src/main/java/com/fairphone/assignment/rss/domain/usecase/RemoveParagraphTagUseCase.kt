package com.fairphone.assignment.rss.domain.usecase

class RemoveParagraphTagUseCase {
    operator fun invoke(input: String): String {
        return if(input.contains("<p>")) input.split("<p>")[1].split("</p>")[0] else input
    }
}
