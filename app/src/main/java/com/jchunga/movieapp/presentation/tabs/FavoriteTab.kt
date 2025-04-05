package com.jchunga.movieapp.presentation.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jchunga.movieapp.domain.entities.DetailMovie
import com.jchunga.movieapp.domain.entities.DetailsEvent
import com.jchunga.movieapp.presentation.components.FavoriteMovieCard

@Composable
fun FavoriteTab(
    movies: List<DetailMovie>,
    navigateToDetail: (DetailMovie) -> Unit,
    userId: String,
    event: (DetailsEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 20.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        items(movies.size){ index ->
            val movie = movies[index]
            FavoriteMovieCard(
                movie =   movie,
                event = {
                    event(it)
                },
                navigateToDetail = {
                    navigateToDetail(it)
                },
                userId = userId
            )

        }
    }
}