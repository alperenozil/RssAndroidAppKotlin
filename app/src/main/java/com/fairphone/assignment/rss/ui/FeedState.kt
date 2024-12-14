package com.fairphone.assignment.rss.ui

import com.fairphone.assignment.rss.data.local.FeedEntity

class FeedState(
    val isLoading: Boolean = false,
    val items: List<FeedEntity>? = emptyList(),
    val error: String? = ""
)