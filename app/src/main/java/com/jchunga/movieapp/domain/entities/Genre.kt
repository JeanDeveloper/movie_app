package com.jchunga.movieapp.domain.entities

data class Genre(
    val id: Long,
    val name: String?
){
    constructor() : this(0, null)
}
