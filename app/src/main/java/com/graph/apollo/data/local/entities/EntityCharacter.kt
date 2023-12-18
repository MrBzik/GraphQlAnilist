package com.graph.apollo.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityCharacter (
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    val age : String?,
    val gender : String?,
    val imageUrlMedium : String?,
    val imageUrlLarge : String? = null,
    val favourites : Int?,
    val nameNative : String? = null,
    val nameFull: String?,
    val description : String? = null,
    val alternativeNames : List<String?>? = null
)


