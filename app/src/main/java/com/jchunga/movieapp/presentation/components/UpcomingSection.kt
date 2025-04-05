package com.jchunga.movieapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.jchunga.movieapp.domain.entities.Movie

@Composable
fun UpcomingMovieSection(
    upcomingMovies: LazyPagingItems<Movie>,
    navigateToDetail: (Movie) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Upcoming",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 12.dp)
        )

        UpcomingMovieList(
            upcomingMovies = upcomingMovies,
            onClick = navigateToDetail
        )

    }

}

@Composable
fun UpcomingMovieList(
    modifier: Modifier = Modifier,
    upcomingMovies: LazyPagingItems<Movie>,
    onClick: (Movie) -> Unit
) {
    val handlePagingResult = handlePagingResult(movies = upcomingMovies)

    if(handlePagingResult){
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(all = 12.dp),
        ){
            items(count = upcomingMovies.itemCount) { index ->
                upcomingMovies[index]?.let { movie ->
                    BestMovieCard(
                        movie = movie,
                        onClick = { onClick(movie) }
                    )
                }
            }
        }
    }

}