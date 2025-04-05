package com.jchunga.movieapp.domain.entities

import com.jchunga.movieapp.domain.usecases.movies.GetCharacters
import com.jchunga.movieapp.domain.usecases.movies.GetDetailMovie
import com.jchunga.movieapp.domain.usecases.movies.GetFavoriteMoviesUseCase
import com.jchunga.movieapp.domain.usecases.movies.GetNotPlayingMovies
import com.jchunga.movieapp.domain.usecases.movies.GetPopularMovies
import com.jchunga.movieapp.domain.usecases.movies.GetUpcomingMovies
import com.jchunga.movieapp.domain.usecases.movies.IsFavoriteMovieUseCase
import com.jchunga.movieapp.domain.usecases.movies.RemoveFavoriteMovieUseCase
import com.jchunga.movieapp.domain.usecases.movies.SaveFavoriteMovieUseCase

data class MoviesUseCases(
    val getNowPlayingMovies: GetNotPlayingMovies,
    val getUpcomingMovies: GetUpcomingMovies,
    val getPopularMovies: GetPopularMovies,
    val getDetailsMovie: GetDetailMovie,
    val getCharacters: GetCharacters,
    val getFavoriteMovies: GetFavoriteMoviesUseCase,
    val saveFavoriteMovie: SaveFavoriteMovieUseCase,
    val removeFavoriteMovie: RemoveFavoriteMovieUseCase,
    val isMovieFavorite: IsFavoriteMovieUseCase
)
