package com.graph.apollo.di

import android.content.SharedPreferences
import com.apollographql.apollo3.ApolloClient
import com.graph.apollo.data.remote.AnimeCharactersClient
import com.graph.apollo.data.remote.ApolloAnimeCharactersClient
import com.graph.apollo.data.remote.FakeAnimeCharactersClient
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
object TestViewModelModule {
    @Provides
    @ViewModelScoped
    fun providesAnimeCharactersClient() : AnimeCharactersClient {
        return FakeAnimeCharactersClient()
    }

    @Provides
    @ViewModelScoped
    fun providesUseCaseGetLastSearchQuery(@Named(AppModule.NAMED_SEARCH_QUERY) sharedPreferences: SharedPreferences) : UseCaseGetLastSearchQuery {
        return UseCaseGetLastSearchQuery(searchQueryPref = sharedPreferences)
    }

    @Provides
    @ViewModelScoped
    fun providesUseCaseSaveLastSearchQuery(@Named(AppModule.NAMED_SEARCH_QUERY) sharedPreferences: SharedPreferences) : UseCaseSaveLastSearchQuery {
        return UseCaseSaveLastSearchQuery(searchQueryPref = sharedPreferences)
    }
}