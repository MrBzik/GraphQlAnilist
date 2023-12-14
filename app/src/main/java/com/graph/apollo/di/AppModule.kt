package com.graph.apollo.di

import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApolloClient() : ApolloClient {

        return ApolloClient.Builder()
            .serverUrl("https://graphql.anilist.co")
            .build()

    }

}