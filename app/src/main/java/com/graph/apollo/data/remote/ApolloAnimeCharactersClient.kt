package com.graph.apollo.data.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.graph.CharactersPageQuery
import com.graph.SingleCharacterByIdQuery
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

        val response =  client
            .query(CharactersPageQuery(
                page = Optional.present(page),
                perPage = Optional.present(perPage),
                sort = Optional.present(null),
                search = Optional.present(search)
            ))
            .execute()

        return response.data?.Page?.characters?.mapNotNull {
            it?.toAnimeCharacterPageItem()
        } ?: emptyList()
    }

    override suspend fun getCharacterById(characterId: Int): AnimeCharacterDescription? {
        return client
            .query(SingleCharacterByIdQuery(Optional.present(characterId)))
            .execute()
            .data?.Character?.toAnimeCharacterDescription()

    }

//    override suspend fun getCharactersByName(search: String): AnimeCharacterDescription? {
//
//
//        val response = client
//            .query(SingleCharacterByNameQuery(Optional.present(search)))
//            .execute()
//
//        Logger.log("${response.exception}")
//
//        return response.data?.Character?.toAnimeCharacterDescription()
//
//    }
}