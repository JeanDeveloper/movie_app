package com.jchunga.movieapp.presentation.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jchunga.movieapp.R
import com.jchunga.movieapp.domain.entities.login.LoginEvent
import com.jchunga.movieapp.domain.entities.user.AuthUser
import com.jchunga.movieapp.presentation.navgraph.Route
import com.jchunga.movieapp.presentation.viewmodels.LoginViewModel
import com.jchunga.movieapp.ui.theme.Orange
import com.jchunga.movieapp.ui.theme.White

@Composable
fun ProfileTab(
    authUser: AuthUser,
    loginViewModel: LoginViewModel,
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.image_not_found),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .padding(20.dp)
                .size(100.dp)
                .clip(CircleShape)
                .border(
                    3.dp,
                    Orange,
                    CircleShape
                ),
            contentScale = ContentScale.Crop
        )

        Text(
            text = authUser.name ?: "",
            style = MaterialTheme.typography.titleLarge.copy(color = White),
        )


        Text(
            text = authUser.email ?: "",
            style = MaterialTheme.typography.titleLarge.copy(color = White),
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                loginViewModel.onEvent(LoginEvent.LogOut)
                navController.navigate(Route.LoginScreen.route){
                    popUpTo(Route.HomeScreen.route){ inclusive = true }
                    popUpTo(0)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(text = "Cerrar Sesión", color = White, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(80.dp))
    }
}