package com.jchunga.movieapp.domain.entities.user

data class AuthUser(
    val id: String,
    val name: String,
    val userName: String?,
    val email: String,
)
