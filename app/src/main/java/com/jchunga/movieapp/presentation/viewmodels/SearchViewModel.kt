package com.jchunga.movieapp.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.jchunga.movieapp.domain.entities.MoviesUseCases
import com.jchunga.movieapp.domain.entities.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import com.jchunga.movieapp.domain.entities.SearchEvent

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val  moviesUseCases: MoviesUseCases
) : ViewModel(){

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state


    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.UpdateSeachQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }
            is SearchEvent.SearchMovies -> {
//                searchMovies()
            }
        }


    }


}