package com.graph.apollo.domain.mappers

import com.graph.CharactersPageQuery
import com.graph.SingleCharacterByIdQuery
import com.graph.apollo.data.local.entities.EntityCharacter
import com.graph.apollo.domain.models.AnimeCharacterDescription
import com.graph.apollo.domain.models.AnimeCharacterPageItem

fun SingleCharacterByIdQuery.Character.toAnimeCharacterDescription(): AnimeCharacterDescription {
    return AnimeCharacterDescription(
        nameNative = name?.native ?: "Unknown",
        imageUrl = image?.large,
        description = description ?: "Nothing to say",
        alternativeNames = name?.alternative,
        nameFull = name?.full ?: "No name?",
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


fun CharactersPageQuery.Character.toEntityCharacter() : EntityCharacter {
    return EntityCharacter(
        id = id,
        age = age,
        gender = gender,
        nameFull = name?.full,
        imageUrlMedium = image?.medium,
        favourites = favourites
    )
}