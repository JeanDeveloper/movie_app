package com.jchunga.movieapp.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.jchunga.movieapp.R
import com.jchunga.movieapp.domain.entities.Movie
import com.jchunga.movieapp.ui.theme.BlueDark2
import com.jchunga.movieapp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    onSearch: (Movie) -> Unit,
    bestMovies: LazyPagingItems<Movie>,
    upcomingMovies: LazyPagingItems<Movie>,
    popularMovies: LazyPagingItems<Movie>,
    onClickFun: () -> Unit = {},
    active: Boolean,
    navigateToDetail: (Movie) -> Unit,
    onClose: ( ) -> Unit = {}
) {

    var query by remember { mutableStateOf("") }
    val allMovies = remember {
        (bestMovies.itemSnapshotList.items +
                upcomingMovies.itemSnapshotList.items +
                popularMovies.itemSnapshotList.items
                ).distinct()
    }
    val filteredMovies = allMovies.filter {
        it.title.contains(query, ignoreCase = true)
    }

    SearchBar(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        query = query,
        onQueryChange = {
            query = it
//            onValueChange(it)
        },
        onSearch = {
            if (filteredMovies.isNotEmpty()) {
                onSearch(filteredMovies.first())
            }
        },
        active = active,
        onActiveChange = {
            onClickFun()
        },
        placeholder = {
            Text(
                text = "Search Movies...",
                style = MaterialTheme.typography.titleLarge,
                color = White,
                modifier = Modifier.padding(start = 10.dp)
            )
        },
        colors = SearchBarDefaults.colors(
            containerColor = BlueDark2,
            inputFieldColors = SearchBarDefaults.inputFieldColors(
                focusedTextColor = White,
                cursorColor = White
            ),
        ),
        leadingIcon = {
            Image(
                painter = painterResource(R.drawable.search),
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            if (active) {
                IconButton(
                    onClick = {
                        onClose()
                        Log.i("OnClick", "CustomSearchBar: SearchBar")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = White
                    )
                }
            } else {
                Image(
                    painter = painterResource(R.drawable.microphone),
                    contentDescription = "Microphone Icon"
                )
            }
        },
    ) {
        if (query.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                items(filteredMovies) { movie ->
                    MovieCardSearchBar(
                        movie = movie,
                        navigateToDetail = {
                            navigateToDetail(it)
                        }
                    )
                }
            }

        }
    }

}

@Composable
fun MovieCardSearchBar(
    movie: Movie,
    navigateToDetail: (Movie) -> Unit
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
                movie.title,
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
            Icon(
                Icons.Default.ArrowForward,
                contentDescription = "Go"
            )
        },
        overlineContent = {
            Text(
                text = movie.originalLanguage,
                style = MaterialTheme.typography.titleSmall,
                color = White
            )
        },
        supportingContent = {
            Text(
                text = movie.releaseDate,
                style = MaterialTheme.typography.titleSmall,
                color = White
            )
        }

    )
}