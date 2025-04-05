package com.jchunga.movieapp.data.repositories

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jchunga.movieapp.core.utils.Constants
import com.jchunga.movieapp.data.remote.FirestoreSource
import com.jchunga.movieapp.data.remote.MovieApi
import com.jchunga.movieapp.data.remote.MoviesPagingSource
import com.jchunga.movieapp.domain.entities.CustomCharacter
import com.jchunga.movieapp.domain.entities.DetailMovie
import com.jchunga.movieapp.domain.entities.Either
import com.jchunga.movieapp.domain.entities.Movie
import com.jchunga.movieapp.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImp(
    private val movieApi: MovieApi,
    private val firestoreSource: FirestoreSource
) : MovieRepository {

    override fun getNowPlayingMovies(language: String) : Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig( pageSize =  20),
            pagingSourceFactory =  {
                MoviesPagingSource(
                    movieApi = movieApi,
                    language = language,
                    movieGetType = 1
                )
            }
        ).flow
    }

    override fun getUpcomingMovies(language: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                MoviesPagingSource(
                    movieApi = movieApi,
                    language = language,
                    movieGetType = 2
                )
            }
        ).flow
    }

    override fun getPopularMovies(language: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                MoviesPagingSource(
                    movieApi = movieApi,
                    language = language,
                    movieGetType = 3
                )
            }
        ).flow
    }

    override fun getDetailMovie(language: String, id: Long): Flow<DetailMovie> {
        return flow {
            val response = movieApi.getDetailMovie(authToken = Constants.API_KEY, language = language, id = id)
            if(response.isSuccessful){
                val detailMovie = response.body()
                Log.d("SUCCESS", "getDetailMovie: $detailMovie")
                if (detailMovie != null) {
                    emit(detailMovie)
                }
            } else {
                Log.e("ERROR", "Codigo HTTP: ${response.code()}")
            }
        }
    }

    override fun getCharacters(language:String, idMovie: Long): Flow<List<CustomCharacter>> {
        return flow {
            val response = movieApi.getCharacters(authToken = Constants.API_KEY, language = language , id = idMovie)
            if(response.isSuccessful){
                val characteres = response.body()
                Log.d("SUCCESS", "getCharacters: $characteres")
                if (characteres != null) {
                    emit(characteres.cast)
                }
            } else{
                Log.e("ERROR GETCHARACTERES", "Codigo HTTP: ${response.body()}")
            }
        }
    }

    override fun getFavoriteMovies(userId: String): Flow<List<DetailMovie>> {
        return flow{
            val response = firestoreSource.getFavoriteMovies(userId)
            if(response is Either.Right){
                emit(response.value)
            }
            if(response is Either.Left){
                Log.e("ERROR", "getFavoriteMovies: ${response.value}")
            }
        }
    }

    override suspend fun saveFavoriteMovie(userId: String, movie: DetailMovie): Boolean {
        return when (val response = firestoreSource.addFavoriteMovie(userId, movie)) {
            is Either.Right -> {
                response.value
            }
            is Either.Left -> {
                false
            }
        }
    }

    override suspend fun removeFavoriteMovie(userId: String, movieId: Long): Boolean {

        return when (val response = firestoreSource.removeFavoriteMovie(userId, movieId)) {
            is Either.Right -> {
                response.value
            }
            is Either.Left -> {
                false
            }
        }

    }

    override suspend fun isMovieFavorite(userId: String, movieId: Long): Flow<Boolean> {

        return flow {
            val response = firestoreSource.isMovieFavorite(userId, movieId)

            if(response is Either.Right){
                emit(response.value)
            }
            if(response is Either.Left) {
                Log.e("ERROR", "isMovieFavorite: ${response.value}")
            }

        }

    }

}