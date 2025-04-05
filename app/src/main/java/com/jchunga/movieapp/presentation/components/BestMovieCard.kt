package com.jchunga.movieapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jchunga.movieapp.domain.entities.Movie
import com.jchunga.movieapp.ui.theme.White

@Composable
fun BestMovieCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    movie: Movie

) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Column(
        modifier = modifier.padding(end = 15.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/${movie.posterPath}",
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(screenWidth / 3)
                    .height(screenHeight / 4)
                    .clickable { onClick() }
            )
        }
        Text(
            movie.title,
            style = MaterialTheme.typography.titleMedium,
            color = White,
            modifier = Modifier.width(screenWidth / 3),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

}
