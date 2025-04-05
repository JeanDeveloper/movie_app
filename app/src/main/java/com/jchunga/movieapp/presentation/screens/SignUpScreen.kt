package com.jchunga.movieapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jchunga.movieapp.R
import com.jchunga.movieapp.core.utils.snackbarUtils
import com.jchunga.movieapp.domain.entities.login.LoginState
import com.jchunga.movieapp.domain.entities.register.RegisterEvent
import com.jchunga.movieapp.domain.entities.register.RegisterState
import com.jchunga.movieapp.presentation.components.CustomButton
import com.jchunga.movieapp.presentation.components.CustomTextField
import com.jchunga.movieapp.presentation.navgraph.Route
import com.jchunga.movieapp.presentation.viewmodels.RegisterViewModel
import com.jchunga.movieapp.ui.theme.BlueDark
import com.jchunga.movieapp.ui.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(
    registerViewModel: RegisterViewModel,
    navController : NavController
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val state by registerViewModel.state.collectAsState()

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 20.dp, vertical = 100.dp)
        ) {

            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var name by remember { mutableStateOf("") }

            IconButton(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape),
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "",
                    tint = White
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = White
            )

            Spacer(modifier = Modifier.height(150.dp))

            CustomTextField(
                placeHolder = "Name",
                value = name,
                onValueChange = {
                    name = it
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomTextField(
                placeHolder = "Email",
                value = email,
                onValueChange = {
                    email = it
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomTextField(
                placeHolder = "Password",
                value = password,
                onValueChange = {
                    password = it
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            CustomButton(
                text = "Register",
                modifier = Modifier
                    .background(BlueDark)
                    .fillMaxWidth()
                    .height(60.dp),
                onClick = {
                    registerViewModel.onEvent(
                        RegisterEvent.Submit(
                            email = email,
                            password = password,
                            name = name
                        )
                    )
                }
            )

        }

    }


    when(state){
        is RegisterState.Idle -> {}
        is RegisterState.Loading -> {
            LoadingScreen()
        }
        is RegisterState.Error -> {
            val errorMessage = (state as LoginState.Error).message
            snackbarUtils(
                scope = scope,
                snackbarHostState = snackbarHostState,
                message = errorMessage
            )
        }
        is RegisterState.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(Route.HomeScreen.route){
                    popUpTo(Route.LoginScreen.route){ inclusive = true}
                }
                registerViewModel.resetState()
            }
        }

    }


}