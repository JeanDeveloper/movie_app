package com.jchunga.movieapp.data.models

import com.jchunga.movieapp.R

sealed class BottomNavItem(
    val route:String,
    val icon:Int,
    val title:String
){
    data object Home :  BottomNavItem(
        route = "HomeTab",
        icon = R.drawable.ic_home,
        title = "Home"
    )

    data object Favorite :  BottomNavItem(
        route = "FavoritesTab",
        icon = R.drawable.ic_lovely,
        title = "Favorites"
    )

//    data object Download : BottomNavItem(
//        route = "DownloadTab",
//        icon = R.drawable.ic_download,
//        title = "Download"
//    )

    data object Profile : BottomNavItem(
        route = "ProfileTab",
        icon = R.drawable.ic_profile,
        title = "Profile"
    )

}
