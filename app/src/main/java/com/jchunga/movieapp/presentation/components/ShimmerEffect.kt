package com.jchunga.movieapp.presentation.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jchunga.movieapp.ui.theme.MovieAppTheme

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue  = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    ).value
    background(color = Color(0xFFC3C3C3).copy(alpha = alpha))
}

@Composable
fun BestMovieCardShimmerEffect(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(horizontal = 3.dp)
    ) {
        Box(
            modifier = modifier
                .clip(MaterialTheme.shapes.small)
                .shimmerEffect()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun BestMovieCardShimmerEffectPreview(){
    MovieAppTheme {
        BestMovieCardShimmerEffect()
    }
}