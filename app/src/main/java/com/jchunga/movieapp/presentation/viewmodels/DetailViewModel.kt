package com.jchunga.movieapp.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jchunga.movieapp.domain.entities.DetailMovie
import com.jchunga.movieapp.domain.entities.DetailsEvent
import com.jchunga.movieapp.domain.entities.MoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases
) : ViewModel() {

    private val _movieId = MutableStateFlow<Long?>(null)

    var sideEffect by mutableStateOf<String?>(null)
        private set

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieDetail = _movieId.filterNotNull().flatMapLatest { idp ->
        moviesUseCases.getDetailsMovie(
            language = "es-PE",
            id = idp
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    val credits = _movieId.filterNotNull().flatMapLatest { idp ->
        moviesUseCases.getCharacters(
            language = "es-PE",
            idMovie = idp
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun loadMovieDetail(id:Long){
        _movieId.value = id
    }

    fun onEvent(event:DetailsEvent){

        when(event){
            is DetailsEvent.InserteDeleteMovie -> {
                viewModelScope.launch {
                    val response = moviesUseCases.isMovieFavorite(event.userId, event.movie.id!!)
                    response.collect { resp ->
                        if (resp) {
                            removeFavoriteMovie(userId = event.userId, movieId = event.movie.id)
                        } else {
                            addFavoriteMovie( userId =  event.userId, movie = event.movie )
                        }
                    }
                }
            }
            DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }

    }

    private suspend fun addFavoriteMovie(userId: String, movie: DetailMovie) {
        moviesUseCases.saveFavoriteMovie(userId, movie)
        sideEffect = "Movie added to favorites"
    }

    private suspend fun removeFavoriteMovie(userId: String, movieId: Long) {
        moviesUseCases.removeFavoriteMovie(userId, movieId)
        sideEffect = "Movie removed from favorites"
    }

}