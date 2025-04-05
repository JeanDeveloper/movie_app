package com.jchunga.movieapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jchunga.movieapp.domain.entities.Genre
import com.jchunga.movieapp.ui.theme.BlueDark2
import com.jchunga.movieapp.ui.theme.White

@Composable
fun DetailGenreSection(
    genres: List<Genre>
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
            .padding(vertical = 8.dp, ),
    ) {
        repeat(genres.size) { index ->
            GenreCard(genre = genres[index])
        }
    }
}

@Composable
fun GenreCard(
    modifier: Modifier = Modifier,
    genre: Genre
) {
    Box(
        modifier = modifier.padding(end = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(BlueDark2)
            .padding(10.dp)
    ){
        Text(genre.name ?: "Genre", color = White)
    }
}