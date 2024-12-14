package com.fairphone.assignment.rss.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fairphone.assignment.rss.common.Resource
import com.fairphone.assignment.rss.data.local.FeedDao
import com.fairphone.assignment.rss.data.local.FeedEntity
import com.fairphone.assignment.rss.domain.usecase.GetFeedUseCase
import com.fairphone.assignment.rss.domain.usecase.RemoveParagraphTagUseCase
import com.fairphone.assignment.rss.domain.usecase.ReplaceHtmlEntityWithNormalCharacterUseCase
import com.fairphone.assignment.rss.domain.usecase.UpdateDatabaseEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getFeedUseCase: GetFeedUseCase,
    private val updateDatabaseEntryUseCase: UpdateDatabaseEntryUseCase,
    private val replaceHtmlEntityWithNormalCharacterUseCase: ReplaceHtmlEntityWithNormalCharacterUseCase,
    private val removeParagraphTagUseCase: RemoveParagraphTagUseCase
) : ViewModel() {

    private val _feedState = MutableStateFlow(value = FeedState())
    val feedState = _feedState.asStateFlow()

    private val _urlState = MutableStateFlow(value = "")
    val urlState = _urlState.asStateFlow()

    init {
        getFeed()
    }

    internal fun getFeed() {
        viewModelScope.launch(Dispatchers.IO) {
            val items = getFeedUseCase.invoke()
            items.collect {
                fixTextProblems(it)
                when (it) {
                    is Resource.Success -> _feedState.value = FeedState(items = it.data )
                    is Resource.Error -> _feedState.value = FeedState(error = it.message)
                    is Resource.Loading -> _feedState.value = FeedState(isLoading = true)
                }
            }
        }
    }

    private fun fixTextProblems(input: Resource<List<FeedEntity>>) {
        input.data?.forEach { item ->
            item.title = replaceHtmlEntityWithNormalCharacterUseCase(item.title)
            item.description = removeParagraphTagUseCase(replaceHtmlEntityWithNormalCharacterUseCase(item.description))
        }
    }

    internal fun setUrl(url: String,entity: FeedEntity){
        _urlState.value = url
        viewModelScope.launch(Dispatchers.IO) {
            updateDatabaseEntryUseCase(entity)
        }
    }
}
