package com.graph.apollo.data

import com.graph.apollo.domain.models.AnimeCharacterDescription
import com.graph.apollo.domain.models.AnimeCharacterPageItem
import com.graph.type.CharacterSort

interface AnimeCharactersClient {

    suspend fun getCharactersPage(page: Int, perPage: Int, sort: CharacterSort, search: String) : List<AnimeCharacterPageItem>

    suspend fun getCharacterById(characterId: Int) : AnimeCharacterDescription?


}