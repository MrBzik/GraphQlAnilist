package com.graph.apollo.data

import com.apollographql.apollo3.ApolloClient
import com.graph.LaunchListQuery
import com.graph.apollo.utils.Logger

class ApolloAnimeCharactersClient(
    private val client: ApolloClient
) : AnimeCharactersClient {

//    override suspend fun getCharactersPage(
//        page: Int,
//        perPage: Int,
//        sort: CharacterSort,
//        search: String
//    ): List<AnimeCharacterPageItem> {
//
//        return client
//            .query(CharactersPageQuery())
//            .execute()
//            .data?.Page?.characters?.mapNotNull {
//                it?.toAnimeCharacterPageItem()
//            } ?: emptyList()
//    }
//
//    override suspend fun getCharacterById(characterId: Int): AnimeCharacterDescription? {
//        return client
//            .query(SingleCharacterByIdQuery())
//            .execute()
//            .data?.Character?.toAnimeCharacterDescription()
//
//    }
//
//    override suspend fun getCharactersByName(search: String): AnimeCharacterDescription? {
//
//
//        val response = client
//            .query(SingleCharacterByNameQuery())
//            .execute()
//
//        Logger.log("${response.exception}")
//
//        return response.data?.Character?.toAnimeCharacterDescription()
//
//    }

    override suspend fun testLaunch() {

       val response =  client.query(LaunchListQuery()).execute()

        Logger.log(response.exception?.stackTraceToString() ?: "")

        Logger.log(response.data?.launches?.launches?.size.toString())
    }
}