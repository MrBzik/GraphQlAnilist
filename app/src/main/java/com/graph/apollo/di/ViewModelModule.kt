package com.graph.apollo.di

import android.content.SharedPreferences
import com.apollographql.apollo3.ApolloClient
import com.graph.apollo.data.remote.AnimeCharactersClient
import com.graph.apollo.data.remote.ApolloAnimeCharactersClient
import com.graph.apollo.di.AppModule.NAMED_SEARCH_QUERY
import com.graph.apollo.usecases.UseCaseGetLastSearchQuery
import com.graph.apollo.usecases.UseCaseSaveLastSearchQuery
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun providesAnimeCharactersClient(client: ApolloClient) : AnimeCharactersClient {
        return ApolloAnimeCharactersClient(client)
    }

    @Provides
    @ViewModelScoped
    fun providesUseCaseGetLastSearchQuery(@Named(NAMED_SEARCH_QUERY) sharedPreferences: SharedPreferences) : UseCaseGetLastSearchQuery{
        return UseCaseGetLastSearchQuery(searchQueryPref = sharedPreferences)
    }

    @Provides
    @ViewModelScoped
    fun providesUseCaseSaveLastSearchQuery(@Named(NAMED_SEARCH_QUERY) sharedPreferences: SharedPreferences) : UseCaseSaveLastSearchQuery {
        return UseCaseSaveLastSearchQuery(searchQueryPref = sharedPreferences)
    }

}