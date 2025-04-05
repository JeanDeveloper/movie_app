package com.jchunga.movieapp.domain.entities

sealed class SearchEvent {

    data class UpdateSeachQuery(val searchQuery: String) : SearchEvent()

    data object SearchMovies : SearchEvent()

}
