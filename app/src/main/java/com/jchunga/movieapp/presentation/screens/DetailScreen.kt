package com.jchunga.movieapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jchunga.movieapp.domain.entities.CustomCharacter
import com.jchunga.movieapp.domain.entities.DetailMovie
import com.jchunga.movieapp.domain.entities.DetailsEvent
import com.jchunga.movieapp.domain.entities.user.AuthUser
import com.jchunga.movieapp.presentation.components.CreditsSection
import com.jchunga.movieapp.presentation.components.DetailGenreSection
import com.jchunga.movieapp.presentation.components.DetailInfoSection
import com.jchunga.movieapp.presentation.components.DetailsTopBar
import com.jchunga.movieapp.presentation.components.SummarySection
import com.jchunga.movieapp.ui.theme.White

@Composable
fun DetailScreen(
    movie: DetailMovie,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit,
    credits: List<CustomCharacter>,
    authUser: AuthUser
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight / 2)
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/${movie.posterPath}",
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(screenHeight / 2)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.8f)
                            )
                        )
                    ),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                        )
                    )
            )
            Text(
                movie.title ?: "Sin Titulo",
                style = MaterialTheme.typography.headlineLarge,
                color = White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 12.dp)
            )
            DetailsTopBar(
                onBackClick = navigateUp,
                onFavClick = {
                    event(
                        DetailsEvent.InserteDeleteMovie(
                            userId = authUser.id,
                            movie = movie
                        )
                    )
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)

        ) {

            DetailInfoSection(
                average = movie.voteAverage,
                time = "119"
            )

            DetailGenreSection(genres = movie.genres ?: emptyList())

            SummarySection(summary = movie.overview ?: "")

            CreditsSection(credits = credits)

        }
    }

}
