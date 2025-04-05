package com.jchunga.movieapp.presentation.navgraph

sealed class Route(
    val route: String,
){
    data object OnBoardingScreen: Route("onBoardingScreen")
    data object HomeScreen: Route("homeScreen")
    data object DetailsScreen: Route("detailScreen")
    data object SearchScreen: Route("searchScreen")
    data object LoginScreen: Route("loginScreen")
    data object SignUpScreen: Route("signUpScreen")
}
