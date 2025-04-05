package com.jchunga.movieapp.data.models

import com.google.gson.annotations.SerializedName
import com.jchunga.movieapp.domain.entities.Movie
import com.jchunga.movieapp.domain.entities.Dates

data class MoviesResponse (
    val dates: Dates,
    val page: Long,

    @SerializedName("results")
    val movies: List<Movie>,

    val totalPages: Long,
    val totalResults: Long
)

