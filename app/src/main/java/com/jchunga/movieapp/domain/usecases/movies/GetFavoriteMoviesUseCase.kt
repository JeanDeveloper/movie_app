package com.jchunga.movieapp.domain.usecases.movies

import com.jchunga.movieapp.domain.repositories.MovieRepository

class GetFavoriteMoviesUseCase(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(userId: String) = movieRepository.getFavoriteMovies(userId)

}