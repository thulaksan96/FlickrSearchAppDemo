package com.example.image_search_ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.image_search_ui.imagedetailsscreens.ImageDetailsScreen
import com.example.image_search_ui.imagedetailsscreens.ImageDetailsScreenViewmodel
import com.example.image_search_ui.imagelistscreens.ImageListScreen
import com.example.image_search_ui.imagelistscreens.ImageListScreenViewmodel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.imageListScreen(
    navController: NavController,
) {
    composable(NavigationRoutes.SearchScreen.route) {

        val viewModel = koinViewModel<ImageListScreenViewmodel>()
        val uiState = viewModel.uiState.collectAsState().value

        ImageListScreen(
            uiState = uiState,
            navigateToDetailsScreen = navController::navigateToImageDetailsScreen,
            changeSearchQuery = viewModel::changeSearchQuery,
            search = viewModel::search
        )
    }
}

fun NavGraphBuilder.imageDetailsScreen(
    navController: NavController,
) {
    composable(route = NavigationRoutes.ImageDetailsScreen.route + "/{imageId}",
        arguments = listOf(
            navArgument("imageId") { type = NavType.StringType }
        )) {

        val viewModel = koinViewModel<ImageDetailsScreenViewmodel>()
        val uiState = viewModel.uiState.collectAsState().value

        LaunchedEffect(Unit) {
            viewModel.setImagesId(it.arguments?.getString("imageId") ?: "")
        }

        ImageDetailsScreen(
            uiState = uiState,
            onBackIconClick = navController::popBackStack
        )
    }
}

fun NavController.navigateToImageDetailsScreen(imageId: String) {
    navigate(NavigationRoutes.ImageDetailsScreen.route + "/" + imageId)
}