package com.graph.apollo.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.graph.apollo.data.local.CharactersLocalSource
import com.graph.apollo.data.local.entities.EntityCharacter
import com.graph.apollo.data.remote.AnimeCharactersClient

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator(
    private val searchQuery: String,
    private val charactersLocalSource : CharactersLocalSource,
    private val charactersRemoteSource : AnimeCharactersClient
) : RemoteMediator<Int, EntityCharacter>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EntityCharacter>
    ): MediatorResult {

        when(loadType){

            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.REFRESH ->{}
            LoadType.APPEND -> {}
        }

        return MediatorResult.Success(endOfPaginationReached = true)

    }
}