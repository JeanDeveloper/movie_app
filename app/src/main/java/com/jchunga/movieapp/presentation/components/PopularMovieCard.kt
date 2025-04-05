package com.jchunga.movieapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.PagerState
import com.jchunga.movieapp.domain.entities.Movie
import kotlin.math.absoluteValue

@Composable
fun PopularMovieCard(
    page : Int,
    pagerState: PagerState,
    movie: Movie,
    onClick: () -> Unit
) {
    val pageOffset  = (pagerState.currentPage - page).absoluteValue
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight / 5)
            .graphicsLayer {
                val scale  = 1f - (pageOffset * 0.2f)
                scaleX = scale
                scaleY = scale
                alpha = 1f - (pageOffset * 0.4f)
            }
            .shadow(8.dp, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
    ) {

        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/${movie.backdropPath}",
            contentDescription = movie.backdropPath,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Text(
            movie.title,
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium
        )

    }
}