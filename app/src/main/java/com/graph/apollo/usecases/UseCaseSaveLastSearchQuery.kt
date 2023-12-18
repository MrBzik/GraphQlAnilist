package com.graph.apollo.usecases

import android.content.SharedPreferences
import com.graph.apollo.utils.SharedPref

class UseCaseSaveLastSearchQuery(private val searchQueryPref: SharedPreferences) {

    operator fun invoke(lastSearchQuery : String){
        searchQueryPref.edit().putString(SharedPref.SEARCH_QUERY.name, lastSearchQuery).apply()
    }

}