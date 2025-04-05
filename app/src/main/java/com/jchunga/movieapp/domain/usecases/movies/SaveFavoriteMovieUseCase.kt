package com.jchunga.movieapp.domain.usecases.movies

import com.jchunga.movieapp.domain.entities.DetailMovie
import com.jchunga.movieapp.domain.repositories.MovieRepository

class SaveFavoriteMovieUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(userId: String, movie: DetailMovie) = movieRepository.saveFavoriteMovie(userId, movie)
}