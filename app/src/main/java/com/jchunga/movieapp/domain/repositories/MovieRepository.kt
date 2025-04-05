package com.jchunga.movieapp.domain.repositories

import androidx.paging.PagingData
import com.jchunga.movieapp.domain.entities.CustomCharacter
import com.jchunga.movieapp.domain.entities.DetailMovie
import com.jchunga.movieapp.domain.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getNowPlayingMovies(language: String) : Flow<PagingData<Movie>>

    fun getUpcomingMovies(language: String) : Flow<PagingData<Movie>>

    fun getPopularMovies(language: String) : Flow<PagingData<Movie>>

    fun getDetailMovie(language: String, id: Long) : Flow<DetailMovie>

    fun getCharacters(language: String, idMovie: Long) : Flow<List<CustomCharacter>>

    fun getFavoriteMovies(userId: String) : Flow<List<DetailMovie>>

    suspend fun saveFavoriteMovie(userId: String, movie: DetailMovie) : Boolean

    suspend fun removeFavoriteMovie(userId: String, movieId: Long) : Boolean

    suspend fun isMovieFavorite(userId: String, movieId: Long) : Flow<Boolean>

}