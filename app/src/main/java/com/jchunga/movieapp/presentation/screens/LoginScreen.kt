package com.jchunga.movieapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jchunga.movieapp.core.utils.snackbarUtils
import com.jchunga.movieapp.domain.entities.login.LoginEvent
import com.jchunga.movieapp.domain.entities.login.LoginState
import com.jchunga.movieapp.presentation.components.CustomButton
import com.jchunga.movieapp.presentation.components.CustomTextField
import com.jchunga.movieapp.presentation.navgraph.Route
import com.jchunga.movieapp.presentation.viewmodels.LoginViewModel
import com.jchunga.movieapp.ui.theme.BlueDark
import com.jchunga.movieapp.ui.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    navController: NavController
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val state by loginViewModel.state.collectAsState()

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 20.dp, vertical = 100.dp),
        ) {

            var email by remember{ mutableStateOf("") }
            var pass by remember{ mutableStateOf("") }

            Text(
                text = "Log In",
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = White
            )

            Spacer(modifier = Modifier.height(150.dp))

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
                value = pass,
                onValueChange = {
                    pass = it
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Forget Your Password?",
                color = White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(100.dp))

            CustomButton(
                text = "Login",
                modifier = Modifier
                    .background(BlueDark)
                    .fillMaxWidth()
                    .height(60.dp),
                onClick = {
                    loginViewModel.onEvent(
                        LoginEvent.Submit(
                            email = email,
                            password = pass
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(70.dp))


            Text(
                text = "Don't have an account?",
                color = White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Register now",
                color = White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().clickable {
                    navController.navigate(Route.SignUpScreen.route)
                },
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,

            )

        }

    }

    when(state){
        is LoginState.Idle -> {}
        is LoginState.Loading -> {
            LoadingScreen()
        }
        is LoginState.Error -> {
            val errorMessage = (state as LoginState.Error).message
            snackbarUtils(
                scope = scope,
                snackbarHostState = snackbarHostState,
                message = errorMessage
            )
        }
        is LoginState.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(Route.HomeScreen.route){
                    popUpTo(Route.LoginScreen.route){ inclusive = true}
                }
                loginViewModel.resetState()
            }
        }
    }

}
