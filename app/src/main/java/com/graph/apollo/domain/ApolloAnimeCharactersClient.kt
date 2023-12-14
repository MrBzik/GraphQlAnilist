package com.graph.apollo.domain

import com.apollographql.apollo3.ApolloClient
import com.graph.CharactersPageQuery
import com.graph.SingleCharacterByIdQuery
import com.graph.SingleCharacterByNameQuery
import com.graph.apollo.domain.mappers.toAnimeCharacterDescription
import com.graph.apollo.domain.mappers.toAnimeCharacterPageItem
import com.graph.apollo.domain.models.AnimeCharacterDescription
import com.graph.apollo.domain.models.AnimeCharacterPageItem
import com.graph.type.CharacterSort

class ApolloAnimeCharactersClient(
    private val client: ApolloClient
) : AnimeCharactersClient {

    override suspend fun getCharactersPage(
        page: Int,
        perPage: Int,
        sort: CharacterSort,
        search: String
    ): List<AnimeCharacterPageItem> {

        return client
            .query(CharactersPageQuery())
            .execute()
            .data?.Page?.characters?.mapNotNull {
                it?.toAnimeCharacterPageItem()
            } ?: emptyList()
    }

    override suspend fun getCharacterById(characterId: Int): AnimeCharacterDescription? {
        return client
            .query(SingleCharacterByIdQuery())
            .execute()
            .data?.Character?.toAnimeCharacterDescription()

    }

    override suspend fun getCharactersByName(search: String): AnimeCharacterDescription? {

        return client
            .query(SingleCharacterByNameQuery())
            .execute()
            .data?.Character?.toAnimeCharacterDescription()
    }
}