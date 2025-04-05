package com.jchunga.movieapp.presentation.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.jchunga.movieapp.data.models.BottomNavItem
import com.jchunga.movieapp.domain.entities.DetailMovie
import com.jchunga.movieapp.domain.entities.DetailsEvent
import com.jchunga.movieapp.domain.entities.Movie
import com.jchunga.movieapp.presentation.navgraph.Route
import com.jchunga.movieapp.presentation.tabs.ExploreTab
import com.jchunga.movieapp.presentation.tabs.FavoriteTab
import com.jchunga.movieapp.presentation.tabs.ProfileTab
import com.jchunga.movieapp.presentation.viewmodels.AuthViewModel
import com.jchunga.movieapp.presentation.viewmodels.DetailViewModel
import com.jchunga.movieapp.presentation.viewmodels.FavoriteMovieViewModel
import com.jchunga.movieapp.presentation.viewmodels.HomeViewModel
import com.jchunga.movieapp.presentation.viewmodels.LoginViewModel
import com.jchunga.movieapp.ui.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieNavigator(
    loginViewModel: LoginViewModel,
    navController: NavController
) {
    val customNavController = rememberNavController()
    val backStackState = customNavController.currentBackStackEntryAsState().value
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route in listOf(
            BottomNavItem.Home.route,
            BottomNavItem.Favorite.route,
            BottomNavItem.Profile.route,
        )
    }
    val authViewModel : AuthViewModel = hiltViewModel()
    val user by authViewModel.user.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if(isBottomBarVisible){
                BottomNavigationBar(navController = customNavController)
            }
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        NavHost(
            navController = customNavController,
            startDestination = BottomNavItem.Home.route
        ) {


            composable(BottomNavItem.Home.route) {
                val homeViewModel : HomeViewModel = hiltViewModel()
                val movies = homeViewModel.movies.collectAsLazyPagingItems()
                val upcomingMovies = homeViewModel.upcomingMovies.collectAsLazyPagingItems()
                val popularMovies = homeViewModel.popularMovies.collectAsLazyPagingItems()
                ExploreTab(
                    bestMovies = movies,
                    upcomingMovies = upcomingMovies,
                    popularMovies = popularMovies,
                    navigateToDetail = { movie ->
                        navigateToDetail(
                            navController = customNavController,
                            movie = movie
                        )
                    }
                )
            }
            composable(BottomNavItem.Favorite.route) {

                val favoriteViewModel : FavoriteMovieViewModel = hiltViewModel()
                val detailHomeViewModel: DetailViewModel = hiltViewModel()

                LaunchedEffect(user?.id){
                    favoriteViewModel.loadFavoriteMovies(userId = user?.id ?: "")
                }

                val favoriteMovies by favoriteViewModel.favoriteMovies.collectAsState()

                if(detailHomeViewModel.sideEffect != null){
                    Toast.makeText(
                        LocalContext.current,
                        detailHomeViewModel.sideEffect,
                        Toast.LENGTH_SHORT
                    ).show()
                    detailHomeViewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }

                FavoriteTab(
                    movies = favoriteMovies ?: emptyList(),
                    navigateToDetail = {movie ->
                        navigateToDetailForFavorite(
                            navController = customNavController,
                            movie = movie
                        )
                    },
                    event = {
                        detailHomeViewModel.onEvent(it)
                        favoriteViewModel.loadFavoriteMovies(userId = user?.id ?: "")
                    },
                    userId = user?.id ?: ""
                )

            }
            composable(BottomNavItem.Profile.route) { ProfileTab(authUser = user!!, loginViewModel = loginViewModel, navController = navController) }
            composable(Route.DetailsScreen.route) {
                customNavController.previousBackStackEntry?.savedStateHandle?.get<Long>("id")?.let { id ->

                    val detailHomeViewModel: DetailViewModel = hiltViewModel()
                    val favoriteViewModel : FavoriteMovieViewModel = hiltViewModel()

                    LaunchedEffect(id) {
                        detailHomeViewModel.loadMovieDetail(id)
                    }

                    val movie by detailHomeViewModel.movieDetail.collectAsState()

                    val credits by detailHomeViewModel.credits.collectAsState()

                    if(detailHomeViewModel.sideEffect != null){
                        Toast.makeText(
                            LocalContext.current,
                            detailHomeViewModel.sideEffect,
                            Toast.LENGTH_SHORT
                        ).show()
                        detailHomeViewModel.onEvent(DetailsEvent.RemoveSideEffect)
                    }

                    movie?.let { movieR ->
                        DetailScreen(
                            movie = movieR,
                            navigateUp = {
                                customNavController.navigateUp()
                            },
                            credits = credits ?: emptyList(),
                            event = {
                                detailHomeViewModel.onEvent(it)
                                favoriteViewModel.loadFavoriteMovies(userId = user?.id ?: "")

                            },
                            authUser = user!!
                        )
                    }

                }
            }
        }

    }
}

private fun navigateToTap( navController: NavController, route: String ){
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen){
                saveState = true
            }
            restoreState    = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetail(navController: NavController, movie: Movie){
    navController.currentBackStackEntry?.savedStateHandle?.set("id", movie.id)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}
private fun navigateToDetailForFavorite(navController: NavController, movie: DetailMovie){
    navController.currentBackStackEntry?.savedStateHandle?.set("id", movie.id)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}


@Composable
fun BottomNavigationBar(navController: NavController){
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favorite,
        BottomNavItem.Profile
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 10.dp,

    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }

                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                    )
                },
                label = {
                    Text(text = item.title)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = White,
                    selectedTextColor = White,
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )

        }

    }

}