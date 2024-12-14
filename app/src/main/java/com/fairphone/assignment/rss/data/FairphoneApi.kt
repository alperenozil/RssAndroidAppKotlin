package com.fairphone.assignment.rss.data

import com.fairphone.assignment.rss.data.remote.FeedDto
import retrofit2.http.GET

interface FairphoneApi {
    @GET(value = "feed/")
    suspend fun getFeed() : FeedDto
}