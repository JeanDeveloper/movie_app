package com.jchunga.movieapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jchunga.movieapp.ui.theme.SecondaryOrange
import com.jchunga.movieapp.ui.theme.White

@Composable
fun SummarySection(
    summary: String
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)
    ) {

        Text(
            "Summary",
            style = MaterialTheme.typography.titleLarge,
            color = SecondaryOrange
        )

        Text(
            summary,
            style = MaterialTheme.typography.titleMedium,
            color = White
        )

    }
}