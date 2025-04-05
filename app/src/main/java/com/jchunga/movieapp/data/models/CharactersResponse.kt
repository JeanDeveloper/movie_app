package com.jchunga.movieapp.data.models

import com.jchunga.movieapp.domain.entities.CustomCharacter

data class CharactersResponse(
    val id: Long,
    val cast: List<CustomCharacter>,
    val crew: List<Any>
)
