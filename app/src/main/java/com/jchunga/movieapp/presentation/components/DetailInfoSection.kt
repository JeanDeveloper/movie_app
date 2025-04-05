package com.jchunga.movieapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jchunga.movieapp.R
import com.jchunga.movieapp.ui.theme.SecondaryOrange
import com.jchunga.movieapp.ui.theme.White

@Composable
fun DetailInfoSection(
    average: Double,
    time: String
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "",
                tint = SecondaryOrange
            )
            Text(
                "$average",
                style = MaterialTheme.typography.titleMedium,
                color = White
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.time),
                contentDescription = "",
                tint = White

            )
            Text(
                "$time min",
                style = MaterialTheme.typography.titleMedium,
                color = White
            )
        }

    }

}