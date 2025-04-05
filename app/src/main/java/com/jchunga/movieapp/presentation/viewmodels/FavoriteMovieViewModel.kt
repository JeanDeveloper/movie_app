package com.jchunga.movieapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jchunga.movieapp.domain.entities.MoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases
) : ViewModel() {

    private val _userId = MutableStateFlow<String?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val favoriteMovies = _userId.flatMapLatest { userId ->
        moviesUseCases.getFavoriteMovies(userId ?: "")
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun loadFavoriteMovies(userId:String){
        viewModelScope.launch {
            _userId.value = null
            delay(100)
            _userId.value = userId
        }
    }

}