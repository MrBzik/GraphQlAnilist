package com.graph.apollo.usecases

import android.content.SharedPreferences
import com.graph.apollo.utils.SharedPref

class UseCaseGetLastSearchQuery(private val searchQueryPref : SharedPreferences) {

    operator fun invoke() : String {
        return searchQueryPref.getString(SharedPref.SEARCH_QUERY.name, "") ?: ""
    }

}