package com.example.flickrsearchappdemo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.image_search_ui.navigation.NavigationRoutes
import com.example.image_search_ui.navigation.NavigationRoutes.SearchScreen
import com.example.image_search_ui.navigation.imageDetailsScreen
import com.example.image_search_ui.navigation.imageListScreen

@Composable
fun MainNavigationHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SearchScreen
    ) {
        imageListScreen(navController = navController)
        imageDetailsScreen(navController = navController)
    }
}
