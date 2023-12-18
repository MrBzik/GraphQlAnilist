package com.graph.apollo.di

import android.content.Context
import android.content.SharedPreferences
import com.apollographql.apollo3.ApolloClient
import com.graph.apollo.utils.SharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    const val NAMED_SEARCH_QUERY = "search query"

    @Provides
    @Singleton
    fun providesApolloClient() : ApolloClient {

        return ApolloClient.Builder()
            .serverUrl("https://graphql.anilist.co")
            .build()
    }


    @Provides
    @Singleton
    @Named(NAMED_SEARCH_QUERY)
    fun providesSearchQuerySharedPref(@ApplicationContext context : Context) : SharedPreferences {
        return context.getSharedPreferences("search_query_pref", Context.MODE_PRIVATE)
    }


}