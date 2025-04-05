package com.jchunga.movieapp.presentation.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.jchunga.movieapp.domain.entities.Movie
import com.jchunga.movieapp.presentation.components.BestMoviesSection
import com.jchunga.movieapp.presentation.components.CardSwipeSection
import com.jchunga.movieapp.presentation.components.CategorySection
import com.jchunga.movieapp.presentation.components.CustomSearchBar
import com.jchunga.movieapp.presentation.components.UpcomingMovieSection


@Composable
fun ExploreTab(
    modifier: Modifier = Modifier,
    bestMovies: LazyPagingItems<Movie>,
    upcomingMovies: LazyPagingItems<Movie>,
    popularMovies: LazyPagingItems<Movie>,
    navigateToDetail: (Movie) -> Unit,
) {

    var isSearchActive by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()){
        if(isSearchActive){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                CustomSearchBar(
                    onValueChange = {},
                    onSearch = {},
                    bestMovies = bestMovies,
                    upcomingMovies = upcomingMovies,
                    popularMovies = popularMovies,
                    active = isSearchActive,
                    navigateToDetail = {
                        navigateToDetail(it)
                    },
                    onClose = { isSearchActive = !isSearchActive}
                )
            }
        } else {
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ){
                item{
                    CustomSearchBar(
                        onValueChange = {},
                        onSearch = {},
                        bestMovies = bestMovies,
                        upcomingMovies = upcomingMovies,
                        popularMovies = popularMovies,
                        onClickFun = { isSearchActive = !isSearchActive },
                        active = isSearchActive,
                        navigateToDetail = {
                            navigateToDetail(it)
                        }
                    )
                }

                item{
                    CardSwipeSection(
                        popularMovies = popularMovies,
                        navigateToDetail = navigateToDetail
                    )
                }

                item{
                    BestMoviesSection(
                        bestMovies = bestMovies,
                        navigateToDetail = navigateToDetail
                    )
                }

                item{
                    CategorySection()
                }

                item{
                    UpcomingMovieSection(
                        upcomingMovies = upcomingMovies,
                        navigateToDetail = navigateToDetail
                    )
                }

            }
        }
    }


}