package com.jchunga.movieapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jchunga.movieapp.R
import com.jchunga.movieapp.ui.theme.White

@Composable
fun DetailsTopBar(
    onBackClick : () -> Unit,
    onFavClick : () -> Unit,
) {

    Row(
        modifier = Modifier
            .statusBarsPadding()
            .padding(12.dp)
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.6f)),
            onClick = onBackClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "",
                tint = White
            )
        }

        IconButton(
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.6f)),
            onClick = onFavClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.fav),
                contentDescription = "",
                tint = White
            )
        }

    }



}