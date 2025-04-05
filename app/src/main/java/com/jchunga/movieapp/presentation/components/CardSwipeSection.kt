package com.jchunga.movieapp.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.jchunga.movieapp.domain.entities.Movie
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

@Composable
fun CardSwipeSection(
    popularMovies: LazyPagingItems<Movie>,
    navigateToDetail: (Movie) -> Unit
) {

    val pagerState = rememberPagerState(initialPage = 0)
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState){
        while (true){
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % 5
            scope.launch {
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    val handlePagingPopularResult = handlePagingPopularResult(movies = popularMovies)

    if(handlePagingPopularResult){
        HorizontalPager(
            state = pagerState,
            count = popularMovies.itemCount,
            contentPadding = PaddingValues(horizontal = 50.dp),
            modifier = Modifier.padding(vertical = 16.dp)
        ) { page ->

            val movie = popularMovies[page]

            PopularMovieCard(
                page = page,
                pagerState = pagerState,
                movie = movie!!,
                onClick = {navigateToDetail(movie)}
            )

        }
    }

}

@Composable
fun handlePagingPopularResult(movies: LazyPagingItems<Movie>) : Boolean{
    val loadState = movies.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error  -> loadState.append  as LoadState.Error
        else -> null
    }
    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerPopularEffect()
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
fun ShimmerPopularEffect(modifier: Modifier = Modifier) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        repeat(2) {
            BestMovieCardShimmerEffect(
                modifier = Modifier
                    .width(screenWidth / 1)
                    .height(screenHeight / 5)
                    .padding(15.dp)
            )
        }
    }
}