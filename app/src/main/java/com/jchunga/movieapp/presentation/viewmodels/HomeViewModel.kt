package com.jchunga.movieapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jchunga.movieapp.domain.entities.MoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases
) : ViewModel() {

    val movies = moviesUseCases.getNowPlayingMovies(
        language = "es-PE"
    ).cachedIn(viewModelScope)

    val upcomingMovies = moviesUseCases.getUpcomingMovies(
        language = "es-PE"
    ).cachedIn(viewModelScope)

    val popularMovies = moviesUseCases.getPopularMovies(
        language = "es-PE"
    ).cachedIn(viewModelScope)


}