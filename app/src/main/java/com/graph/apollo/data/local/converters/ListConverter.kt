package com.graph.apollo.data.local.converters

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

class ListConverter {
    @TypeConverter
    fun fromList(value : List<String?>?) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<List<String?>?>(value)

}