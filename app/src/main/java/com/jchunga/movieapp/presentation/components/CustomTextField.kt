package com.jchunga.movieapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jchunga.movieapp.ui.theme.BlueDark
import com.jchunga.movieapp.ui.theme.BlueDark2
import com.jchunga.movieapp.ui.theme.White

@Composable
fun CustomTextField(
    placeHolder: String = "",
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    value: String
) {

    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        modifier = modifier
            .fillMaxWidth()
            .background(BlueDark)
            .clip(RoundedCornerShape(20.dp)),
        singleLine = true,
        placeholder = {
            Text(
                text = placeHolder,
                color = White,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.ExtraBold
            )
        },
        textStyle = MaterialTheme.typography.titleLarge.copy(
            color = White
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = BlueDark2,
            unfocusedContainerColor = BlueDark2,
            disabledContainerColor = BlueDark2,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = White
        )
    )

}
