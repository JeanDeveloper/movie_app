package com.jchunga.movieapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jchunga.movieapp.R
import com.jchunga.movieapp.presentation.components.CustomButton
import com.jchunga.movieapp.ui.theme.White

@Composable
fun OnBoardingScreen(
    onComplete: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        val (imageBack, content) = createRefs()
        Image(
            painterResource(R.drawable.onboarding_image),
            contentDescription = "Image Onboarding",
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(imageBack) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .constrainAs(content) {
                    top.linkTo(imageBack.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Discovery Your", style = MaterialTheme.typography.headlineLarge, color = White)
            Text(
                "Favorite Show",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                "Watch Now or Watch Later",
                style = MaterialTheme.typography.headlineLarge,
                color = White
            )
            Text(
                "you can browse movies and shows by\ngenre, search for specific title, or\ncheck out our recommendation for you ",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 24.dp),
                color = White
            )
            CustomButton(
                text = "Get Started",
                modifier = Modifier.fillMaxWidth().height(60.dp),
                onClick = onComplete
            )
        }

    }

}


@Preview
@Composable
private fun OnBoardingScreenPreview() {
    OnBoardingScreen(
        onComplete = {}
    )
}