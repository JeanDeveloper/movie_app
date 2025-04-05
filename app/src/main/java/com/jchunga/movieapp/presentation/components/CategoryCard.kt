package com.jchunga.movieapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jchunga.movieapp.domain.entities.Category
import com.jchunga.movieapp.ui.theme.BlueDark2
import com.jchunga.movieapp.ui.theme.White

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    category: Category
) {
    Box(
        modifier = modifier.padding(end = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(BlueDark2)
            .padding(10.dp)

    ){
        Text(category.title, color = White)

    }
}