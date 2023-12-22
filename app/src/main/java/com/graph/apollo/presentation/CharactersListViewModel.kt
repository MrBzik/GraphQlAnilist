package com.graph.apollo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.graph.apollo.data.remote.AnimeCharactersClient
import com.graph.apollo.domain.CharactersPageLoader
import com.graph.apollo.domain.CharactersPagingSource
import com.graph.apollo.domain.Messages
import com.graph.apollo.domain.models.AnimeCharacterPageItem
import com.graph.apollo.usecases.UseCaseGetLastSearchQuery
import com.graph.apollo.usecases.UseCaseSaveLastSearchQuery
import com.graph.apollo.utils.Logger
import com.graph.type.CharacterSort
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val charactersClient: AnimeCharactersClient,
    private val getLastSearchQuery: UseCaseGetLastSearchQuery,
    private val saveLastSearchQuery: UseCaseSaveLastSearchQuery
) : ViewModel() {


    private val searchQuery = MutableStateFlow(getLastSearchQuery())

    @OptIn(ExperimentalCoroutinesApi::class)
    val charactersPagingFlow = searchQuery.flatMapLatest { query ->
        getNewCharactersPagingFlow(query )
    }.cachedIn(viewModelScope)


    private val messagesChannel = Channel<Messages>()

    val messagesFlow = messagesChannel.receiveAsFlow()

    private suspend fun loadNewCharactersPage(page: Int, perPage: Int, sort : CharacterSort = CharacterSort.RELEVANCE, search: String) : List<AnimeCharacterPageItem> {
        return  charactersClient.getCharactersPage(page, perPage, sort, search)
    }

    private suspend fun getNewCharactersPagingFlow(query: String) : Flow<PagingData<AnimeCharacterPageItem>> {


        val charactersLoader : CharactersPageLoader = { pageIndex, pageSize ->
            loadNewCharactersPage(page = pageIndex, perPage = pageSize, search = query)
        }
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            initialKey = 1,
            pagingSourceFactory = {
                CharactersPagingSource(charactersLoader)
            }
        ).flow
    }


    fun updateSearchQuery(value : String) {

        if(value == searchQuery.value){
            viewModelScope.launch {
                messagesChannel.send(Messages.ERROR_SAME_QUERY)
            }
            return
        }
        searchQuery.value = value

        saveLastSearchQuery(value)
    }

    fun getSearchQuery() = searchQuery.value



}