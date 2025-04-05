package com.jchunga.movieapp.presentation.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun DownloadTab(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ){
        Text("Download Tab")
    }
}