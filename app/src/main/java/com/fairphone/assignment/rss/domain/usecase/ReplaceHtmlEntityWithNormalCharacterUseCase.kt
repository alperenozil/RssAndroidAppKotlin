package com.fairphone.assignment.rss.domain.usecase

class ReplaceHtmlEntityWithNormalCharacterUseCase {
    operator fun invoke(input: String): String = input.replace("&#8220", "“")
        .replace("&#8221", "”").replace("&#8211", "’")
        .replace("&#8217;", "’").replace("&#8230;", "…")
}