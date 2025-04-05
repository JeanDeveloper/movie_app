package com.jchunga.movieapp.domain.entities


import com.google.gson.annotations.SerializedName

data class DetailMovie (
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @SerializedName("genres")
    val genres: List<Genre>? = null,

    val id: Long? = null,

    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @SerializedName("original_title")
    val originalTitle: String? = null,

    val overview: String? = null,
    val popularity: Double? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null,

    val runtime: Int? = null,

    val status: String? = null,
    val title: String? = null,
    val video: Boolean? = null,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Long
){
    constructor() : this(false, null, null, null, null, null, null, null, null, null, null, null, null, null, 0.0, 0)
}