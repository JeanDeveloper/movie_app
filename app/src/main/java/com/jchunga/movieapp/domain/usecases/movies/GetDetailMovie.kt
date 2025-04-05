package com.jchunga.movieapp.domain.usecases.movies

import com.jchunga.movieapp.domain.entities.DetailMovie
import com.jchunga.movieapp.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetDetailMovie(
    private val movieRepository : MovieRepository
) {
    operator fun invoke(language:String, id:Long) : Flow<DetailMovie> = movieRepository.getDetailMovie(language = language, id = id)
}