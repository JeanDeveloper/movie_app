package com.jchunga.movieapp.presentation.components

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.jchunga.movieapp.R
import com.jchunga.movieapp.domain.entities.CustomCharacter
import com.jchunga.movieapp.ui.theme.SecondaryOrange

@Composable
fun CreditsSection(
    credits: List<CustomCharacter>
) {
    val actors = credits.filter { it.knownForDepartment == "Acting" }
    val directors = credits.filter { it.knownForDepartment == "Directing" }
    val producers = credits.filter { it.knownForDepartment == "Production" }
    val writers = credits.filter { it.knownForDepartment == "Writing" }
    val cameras = credits.filter { it.knownForDepartment == "Camera" }
    val sound = credits.filter { it.knownForDepartment == "Sound" }
    val art = credits.filter { it.knownForDepartment == "Art" }
    val editing = credits.filter { it.knownForDepartment == "Editing" }

    val scrollState = rememberScrollState()

    if(directors.isNotEmpty()) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            Text(
                "Director",
                style = MaterialTheme.typography.titleLarge,
                color = SecondaryOrange
            )

            Row {
                directors.forEach { director ->
                    AvatarCharacter(imageUrl = director.profilePath, director.originalName)
                }
            }
        }
    }

    if(actors.isNotEmpty()) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)
        ) {
            Text(
                "Actors",
                style = MaterialTheme.typography.titleLarge,
                color = SecondaryOrange
            )

            Row(
                modifier = Modifier.horizontalScroll(scrollState)
            ) {
                actors.forEach { actor ->
                    AvatarCharacter(imageUrl = actor.profilePath, actor.originalName)
                }
            }
        }
    }

}

@Composable
fun AvatarCharacter(imageUrl:String?, name:String) {

    val placeHolder = painterResource(id = R.drawable.image_not_found)

    Column {
        Image(
            painter = rememberAsyncImagePainter(
                model = if(imageUrl.isNullOrEmpty()) R.drawable.image_not_found else "https://image.tmdb.org/t/p/w500/$imageUrl",
                error = placeHolder,
                placeholder = placeHolder
            ),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .padding(10.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray, CircleShape)
            ,
            contentScale = ContentScale.Crop
        )

        Text(
            name,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            modifier = Modifier.width(120.dp),
            maxLines = 3,
            textAlign = TextAlign.Center
        )

    }


}