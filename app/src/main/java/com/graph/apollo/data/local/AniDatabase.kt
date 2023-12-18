package com.graph.apollo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.graph.apollo.data.local.converters.ListConverter
import com.graph.apollo.data.local.entities.EntityCharacter

@Database(entities = [EntityCharacter::class],
    version = 1)
@TypeConverters(ListConverter::class)
abstract class AniDatabase : RoomDatabase() {

    abstract val charactersDao : CharactersDao

}