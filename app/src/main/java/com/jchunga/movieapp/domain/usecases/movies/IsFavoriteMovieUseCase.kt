package com.jchunga.movieapp.domain.usecases.movies

import com.jchunga.movieapp.domain.repositories.MovieRepository

class IsFavoriteMovieUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(userId: String, movieId: Long) = movieRepository.isMovieFavorite(userId = userId, movieId = movieId)

}