package com.graph.apollo.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.graph.apollo.data.local.entities.EntityCharacter

@Dao
interface CharactersDao {

    @Upsert
    suspend fun insertEntry(character: EntityCharacter)

    @Query("DELETE FROM EntityCharacter WHERE ID =:id")
    suspend fun deleteEntry(id: Int)

    @Query("SELECT id, age, gender, nameFull, imageUrlMedium, favourites FROM EntityCharacter")
    fun characterPagingSource() : PagingSource<Int, EntityCharacter>

}