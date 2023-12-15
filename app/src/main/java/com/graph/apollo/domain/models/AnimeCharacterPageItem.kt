package com.graph.apollo.domain.models

data class AnimeCharacterPageItem(
    val id : Int,
    val age : String,
    val gender : String,
    val name : String,
    val imageUrl : String?,
    val favourites : Int
)


