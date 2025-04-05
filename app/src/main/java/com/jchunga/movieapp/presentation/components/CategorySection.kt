package com.jchunga.movieapp.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jchunga.movieapp.domain.entities.Category
import kotlinx.coroutines.delay

@Composable
fun CategorySection() {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Category",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 12.dp)
        )

        CategoryList()

    }

}

@Composable
fun CategoryList(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    var isLoading by remember{ mutableStateOf(true) }

    LaunchedEffect(Unit){
        delay(2000)
        isLoading = false
    }

    val categories = listOf(
        Category(
            title = "Action",
            description = "Action movies"
        ),
        Category(
            title = "Comedy",
            description = "Comedy movies"
        ),
        Category(
            title = "Drama",
            description = "Drama movies"
        ),
        Category(
            title = "Horror",
            description = "Horror movies"
            ),
        Category(
            title = "Romance",
            description = "Romance movies"
        ),
        Category(
            title = "Thriller",
            description = "Thriller movies"
        ),
        Category(
            title = "Adventure",
            description = "Adventure movies"
        ),
        Category(
            title = "Animation",
            description = "Animation movies"
        ),
        Category(
            title = "Crime",
            description = "Crime movies"
        ),
        Category(
            title = "Documentary",
            description = "Documentary movies"
            ),
        Category(
            title = "Family",
            description = "Family movies"
        ),
    )

    if(isLoading){
        CategoryCardShimmer()
    } else {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
                .padding(12.dp),
        ) {
            repeat(categories.size){ index ->
                CategoryCard(category = categories[index])
            }
        }
    }
}


@Composable
fun CategoryCardShimmer(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth().padding(13.dp)
    ) {
        repeat(5) {
            BestMovieCardShimmerEffect(
                modifier = Modifier
                    .width(90.dp)
                    .height(40.dp)
            )
        }
    }
}

