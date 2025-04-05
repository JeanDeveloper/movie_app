package com.jchunga.movieapp.domain.usecases.movies

import androidx.paging.PagingData
import com.jchunga.movieapp.domain.entities.Movie
import com.jchunga.movieapp.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMovies(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(language: String) : Flow<PagingData<Movie>> = movieRepository.getPopularMovies(language = language)
}