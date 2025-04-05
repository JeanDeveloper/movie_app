package com.jchunga.movieapp.domain.entities

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val movies: Flow<PagingData<Movie>>? = null,
)
