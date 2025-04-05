package com.jchunga.movieapp.domain.usecases.movies

import com.jchunga.movieapp.domain.entities.CustomCharacter
import com.jchunga.movieapp.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetCharacters(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(language:String, idMovie:Long) : Flow<List<CustomCharacter>> = movieRepository.getCharacters(language = language, idMovie = idMovie)
}