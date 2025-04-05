package com.jchunga.movieapp.data.remote

import com.jchunga.movieapp.data.models.CharactersResponse
import com.jchunga.movieapp.data.models.MoviesResponse
import com.jchunga.movieapp.domain.entities.DetailMovie
import com.jchunga.movieapp.domain.entities.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getMovies(
        @Header("Authorization") authToken : String,
        @Header("Accept") accept : String = "application/json",
        @Query("language") language: String,
        @Query("page") page: Int
    ) : MoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Header("Authorization") authToken : String,
        @Header("Accept") accept : String = "application/json",
        @Query("language") language: String,
        @Query("page") page: Int
    ) : MoviesResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Header("Authorization") authToken : String,
        @Header("Accept") accept : String = "application/json",
        @Query("language") language: String,
        @Query("page") page: Int
    ) : MoviesResponse

    @GET("movie/{id}")
    suspend fun getDetailMovie(
        @Header("Authorization") authToken : String,
        @Header("Accept") accept : String = "application/json",
        @Path("id") id: Long,
        @Query("language") language: String,
    ) : Response<DetailMovie>

    @GET("movie/{id}/credits")
    suspend fun getCharacters(
        @Header("Authorization") authToken : String,
        @Header("Accept") accept : String = "application/json",
        @Path("id") id: Long,
        @Query("language") language: String
    ): Response<CharactersResponse>

}