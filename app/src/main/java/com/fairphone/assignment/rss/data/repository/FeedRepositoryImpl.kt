package com.fairphone.assignment.rss.data.repository

import com.fairphone.assignment.rss.common.Resource
import com.fairphone.assignment.rss.data.FairphoneApi
import com.fairphone.assignment.rss.data.local.FeedDao
import com.fairphone.assignment.rss.data.local.FeedEntity
import com.fairphone.assignment.rss.data.remote.FeedDto
import com.fairphone.assignment.rss.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val api: FairphoneApi,
    private val dao: FeedDao
) : FeedRepository {

    override suspend fun getFeed() : Flow<Resource<List<FeedEntity>>> = flow {
        try {
            emit(Resource.Loading())
            val resultFromCache = dao.getFeed()
            if (resultFromCache.isEmpty()){
                val result = api.getFeed() //.map { it.toModel() }
                result.channel.item.forEach {
                    dao.insert(
                        FeedEntity(
                            creator = it.creator,
                            pubDate = it.pubDate,
                            title = it.title,
                            description = it.description,
                            link = it.link
                        )
                    )
                }
                emit(Resource.Success(dao.getFeed()))
            } else {
                emit(Resource.Success(resultFromCache))
            }
        } catch (e: HttpException) { // if response code doesnt start with 2
            emit(Resource.Error(e.localizedMessage ?: "unexpected error"))
        } catch (e: IOException) { // no internet connection
            emit(Resource.Error(e.localizedMessage ?: "network error"))
        }
    }

    override suspend fun updateDatabaseEntry(entry: FeedEntity) {
        return dao.update(entry)
    }

}