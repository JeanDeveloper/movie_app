package com.jchunga.movieapp.domain.entities

sealed class DetailsEvent {
    data class InserteDeleteMovie( val userId: String, val movie: DetailMovie): DetailsEvent()
    data object RemoveSideEffect: DetailsEvent()
}