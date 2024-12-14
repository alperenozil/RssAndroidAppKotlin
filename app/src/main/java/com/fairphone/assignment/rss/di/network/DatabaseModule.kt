package com.fairphone.assignment.rss.di.network

import android.content.Context
import androidx.room.Room
import com.fairphone.assignment.rss.data.local.AppDatabase
import com.fairphone.assignment.rss.data.local.FeedDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFeedDao(appDatabase: AppDatabase): FeedDao {
        return appDatabase.feedDao()
    }
}