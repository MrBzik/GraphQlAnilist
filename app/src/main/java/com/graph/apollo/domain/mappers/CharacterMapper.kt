package com.graph.apollo.domain.mappers

import com.graph.CharactersPageQuery
import com.graph.SingleCharacterByIdQuery
import com.graph.SingleCharacterByNameQuery
import com.graph.apollo.domain.models.AnimeCharacterDescription
import com.graph.apollo.domain.models.AnimeCharacterPageItem

fun SingleCharacterByIdQuery.Character.toAnimeCharacterDescription(): AnimeCharacterDescription {
    return AnimeCharacterDescription(
        nameNative = name?.native ?: "Unknown",
        imageUrl = image?.large,
        description = description ?: "Nothing to say"
    )
}

fun SingleCharacterByNameQuery.Character.toAnimeCharacterDescription(): AnimeCharacterDescription {
    return AnimeCharacterDescription(
        nameNative = name?.native ?: "Nobody",
        imageUrl = image?.large,
        description = description ?: "Nothing to say"
    )
}


fun CharactersPageQuery.Character.toAnimeCharacterPageItem() : AnimeCharacterPageItem {
    return AnimeCharacterPageItem(
        id = id,
        age = age ?: "Unknown",
        gender = gender ?: "Unknown",
        name = name?.full ?: "Unknown",
        imageUrl = image?.medium,
        favourites = favourites ?: 0
    )
}