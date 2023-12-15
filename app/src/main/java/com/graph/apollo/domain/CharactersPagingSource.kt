package com.graph.apollo.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.graph.apollo.domain.models.AnimeCharacterPageItem


typealias CharactersPageLoader = suspend (pageIndex: Int, pageSize: Int) -> List<AnimeCharacterPageItem>

class CharactersPagingSource(
    val loader : CharactersPageLoader
) : PagingSource<Int, AnimeCharacterPageItem>() {


    override fun getRefreshKey(state: PagingState<Int, AnimeCharacterPageItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeCharacterPageItem> {

        val pageNumber = params.key ?: 1

        return try {
            val characters = loader(pageNumber, params.loadSize)

            LoadResult.Page(
                data = characters,
                prevKey = null,
                nextKey = if(characters.size < params.loadSize) null else pageNumber + 1
            )

        } catch (e : Exception){
            LoadResult.Error(e)
        }
    }
}