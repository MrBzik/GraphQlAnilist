package com.graph.apollo.di

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    const val NAMED_SEARCH_QUERY = "search query"

//    @Provides
//    @Singleton
//    fun providesApolloClient() : ApolloClient {
//
//        return ApolloClient.Builder()
//            .serverUrl("https://graphql.anilist.co")
//            .build()
//    }


    @Provides
    @Singleton
    @Named(NAMED_SEARCH_QUERY)
    fun providesSearchQuerySharedPref() : SharedPreferences {

        val context = ApplicationProvider.getApplicationContext<Context>()
//        val context = InstrumentationRegistry.getInstrumentation().targetContext

        return context.getSharedPreferences("search_query_pref", Context.MODE_PRIVATE)
    }


}