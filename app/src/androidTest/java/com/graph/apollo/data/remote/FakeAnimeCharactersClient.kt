package com.graph.apollo.data.remote

import com.graph.apollo.domain.models.AnimeCharacterDescription
import com.graph.apollo.domain.models.AnimeCharacterPageItem
import com.graph.apollo.utils.Logger
import com.graph.type.CharacterSort
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.math.min

class FakeAnimeCharactersClient : AnimeCharactersClient {

    private val fakeList = List(200){ index ->
        AnimeCharacterPageItem(
            id = index,
            age = index.toString(),
            gender = index.toString(),
            name = index.toString(),
            imageUrl = index.toString(),
            favourites = index
        )
    }

    override suspend fun getCharactersPage(
        page: Int,
        perPage: Int,
        sort: CharacterSort,
        search: String
    ): List<AnimeCharacterPageItem> {


        delay(1000)

        if(search.isBlank()) return emptyList()

        val fromIndex = min((page - 1) * 10, fakeList.lastIndex)
        val toIndex = min(fromIndex + perPage, fakeList.size)

        return try {
            fakeList.subList(fromIndex, toIndex)
        } catch (e : Exception){
            Logger.log(e.stackTraceToString())
            emptyList()
        }
    }

    override suspend fun getCharacterById(characterId: Int): AnimeCharacterDescription? {

        delay(1000)

        val match = fakeList.find {
            it.id == characterId
        }

        return AnimeCharacterDescription(
            nameNative = match?.name ?: "No name?",
            nameFull = match?.name ?: "No name?",
            imageUrl = match?.name,
        )
    }
}