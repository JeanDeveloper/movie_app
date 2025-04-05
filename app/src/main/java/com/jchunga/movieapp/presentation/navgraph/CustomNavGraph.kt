package com.jchunga.movieapp.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jchunga.movieapp.presentation.screens.LoginScreen
import com.jchunga.movieapp.presentation.screens.MovieNavigator
import com.jchunga.movieapp.presentation.screens.OnBoardingScreen
import com.jchunga.movieapp.presentation.screens.SignUpScreen
import com.jchunga.movieapp.presentation.viewmodels.AuthViewModel
import com.jchunga.movieapp.presentation.viewmodels.LoginViewModel
import com.jchunga.movieapp.presentation.viewmodels.OnboardingViewModel
import com.jchunga.movieapp.presentation.viewmodels.RegisterViewModel


@Composable
fun CustomNavGraph(
    navController: NavHostController
) {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val authViewModel : AuthViewModel = hiltViewModel()
    val onboardingViewModel: OnboardingViewModel = hiltViewModel()
    val registerViewModel: RegisterViewModel = hiltViewModel()

    val isAuthenticated by authViewModel.isAuthenticated.collectAsState()
    val onboardingCompleted by onboardingViewModel.onboardingCompleted.collectAsState()

    if (onboardingCompleted == null) { return }


    val startDestination = when {
        onboardingCompleted == false -> Route.OnBoardingScreen.route
        !isAuthenticated -> Route.LoginScreen.route
        else -> Route.HomeScreen.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(
            route = Route.SignUpScreen.route
        ) {
            SignUpScreen(
                navController = navController,
                registerViewModel = registerViewModel
            )
        }

        composable(
            route = Route.OnBoardingScreen.route
        ) {
            OnBoardingScreen(
                onComplete = {
                    onboardingViewModel.saveOnboardingCompleted()
                    navController.navigate(Route.LoginScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }
        composable(
            route = Route.LoginScreen.route
        ){
            LoginScreen(
                navController = navController,
                loginViewModel = loginViewModel
            )
        }

        composable(
            route = Route.HomeScreen.route
        ){
            MovieNavigator(
                loginViewModel = loginViewModel,
                navController = navController,
            )
        }
    }

}