package com.jchunga.movieapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jchunga.movieapp.domain.entities.DetailMovie
import com.jchunga.movieapp.domain.entities.DetailsEvent
import com.jchunga.movieapp.ui.theme.BlueDark2
import com.jchunga.movieapp.ui.theme.White

@Composable
fun FavoriteMovieCard(
    movie: DetailMovie,
    navigateToDetail: (DetailMovie) -> Unit,
    event: (DetailsEvent) -> Unit,
    userId: String
) {
    ListItem(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                navigateToDetail(movie)
            },
        colors = ListItemDefaults.colors(
            containerColor = BlueDark2
        ),
        headlineContent = {
            Text(
                movie.title ?: "",
                style = MaterialTheme.typography.titleLarge,
                color = White
            )
        },
        leadingContent = {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/${movie.backdropPath}",
                contentDescription = movie.backdropPath,
                modifier = Modifier
                    .height(100.dp)
                    .width(50.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        },
        trailingContent = {
            IconButton(
                onClick = {
                    event(
                        DetailsEvent.InserteDeleteMovie(
                            userId = userId,
                            movie = movie
                        )
                    )
                }
            ) {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = "Go",
                    tint = White
                )
            }
        },
        overlineContent = {
            Text(
                text = movie.originalLanguage ?: "",
                style = MaterialTheme.typography.titleSmall,
                color = White
            )
        },
        supportingContent = {
            Text(
                text = movie.releaseDate ?: "",
                style = MaterialTheme.typography.titleSmall,
                color = White
            )
        }
    )
}
