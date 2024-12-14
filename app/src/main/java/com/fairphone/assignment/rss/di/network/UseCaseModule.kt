package com.fairphone.assignment.rss.di.network

import com.fairphone.assignment.rss.domain.usecase.RemoveParagraphTagUseCase
import com.fairphone.assignment.rss.domain.usecase.ReplaceHtmlEntityWithNormalCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideReplaceHtmlEntityWithNormalCharacterUseCase() : ReplaceHtmlEntityWithNormalCharacterUseCase = ReplaceHtmlEntityWithNormalCharacterUseCase()

    @Provides
    @Singleton
    fun removeParagraphTagUseCase() : RemoveParagraphTagUseCase = RemoveParagraphTagUseCase()
}