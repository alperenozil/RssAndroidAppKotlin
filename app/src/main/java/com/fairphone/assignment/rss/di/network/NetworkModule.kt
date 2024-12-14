package com.fairphone.assignment.rss.di.network

import com.fairphone.assignment.rss.common.Constants.BASE_URL
import com.fairphone.assignment.rss.data.FairphoneApi
import com.fairphone.assignment.rss.data.local.FeedDao
import com.fairphone.assignment.rss.data.repository.FeedRepositoryImpl
import com.fairphone.assignment.rss.domain.repository.FeedRepository
import com.fairphone.assignment.rss.domain.usecase.RemoveParagraphTagUseCase
import com.fairphone.assignment.rss.domain.usecase.ReplaceHtmlEntityWithNormalCharacterUseCase
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideFairphoneApi(): FairphoneApi =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            TikXmlConverterFactory.create(
                TikXml.Builder().exceptionOnUnreadXml(false).build()
            )
        ).client(OkHttpClient()).build().create(FairphoneApi::class.java)

    @Provides
    @Singleton
    fun provideFeedRepository(api: FairphoneApi, dao: FeedDao): FeedRepository = FeedRepositoryImpl(
        api,
        dao
    )
}