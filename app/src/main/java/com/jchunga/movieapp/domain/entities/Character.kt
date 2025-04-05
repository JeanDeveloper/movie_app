package com.jchunga.movieapp.domain.entities

import com.google.gson.annotations.SerializedName

data class CustomCharacter(
    val adult: Boolean,
    val gender: Long,
    val id: Long,

    @SerializedName("known_for_department")
    val knownForDepartment: String,
    val name: String,

    @SerializedName("original_name")
    val originalName: String,
    val popularity: Double,

    @SerializedName("profile_path")
    val profilePath: String? = null,

    @SerializedName("cast_id")
    val castID: Long,
    val character: String,

    @SerializedName("credit_id")
    val creditID: String,
    val order: Long
)
