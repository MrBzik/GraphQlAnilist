package com.graph.apollo.di

import com.apollographql.apollo3.ApolloClient
import com.graph.apollo.data.AnimeCharactersClient
import com.graph.apollo.data.ApolloAnimeCharactersClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun providesAnimeCharactersClient(client: ApolloClient) : AnimeCharactersClient {
        return ApolloAnimeCharactersClient(client)
    }

}