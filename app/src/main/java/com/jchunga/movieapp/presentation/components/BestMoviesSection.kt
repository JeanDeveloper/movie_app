package com.jchunga.movieapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.jchunga.movieapp.domain.entities.Movie

@Composable
fun BestMoviesSection(
    bestMovies: LazyPagingItems<Movie>,
    navigateToDetail: (Movie) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            "Best Movies",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 12.dp)
        )

        BestMovieList(
            movies = bestMovies,
            onClick = navigateToDetail
        )

    }
}

@Composable
fun BestMovieList(
    modifier: Modifier = Modifier,
    movies: LazyPagingItems<Movie>,
    onClick: (Movie) -> Unit
){
    val handlePagingResult = handlePagingResult(movies = movies)

    if(handlePagingResult){
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(all = 12.dp),
        ) {
            items(count = movies.itemCount){ index ->
                movies[index]?.let { movie ->
                    BestMovieCard(
                        movie = movie,
                        onClick = { onClick(movie) }
                    )

                }
            }
        }
    }

}

@Composable
fun handlePagingResult(movies: LazyPagingItems<Movie>) : Boolean{
    val loadState = movies.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error  -> loadState.append  as LoadState.Error
        else -> null
    }
    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        error != null -> {

            false
        }
        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect(modifier: Modifier = Modifier) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        repeat(3) {
            BestMovieCardShimmerEffect(
                modifier = Modifier
                    .width(screenWidth / 3)
                    .height(screenHeight / 4)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShimmerEffectPreview() {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        ShimmerEffect()
    }
}