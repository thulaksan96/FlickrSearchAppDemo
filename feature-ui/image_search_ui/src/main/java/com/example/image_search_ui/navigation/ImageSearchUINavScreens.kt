package com.example.image_search_ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.image_search_ui.imagedetailsscreens.ImageDetailsScreen
import com.example.image_search_ui.imagedetailsscreens.ImageDetailsViewmodel
import com.example.image_search_ui.imagelistscreens.ImageListScreen
import com.example.image_search_ui.imagelistscreens.ImageListViewmodel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.imageListScreen(
    navController: NavController,
) {
    composable(NavigationRoutes.SearchScreen.route) {

        val viewModel = koinViewModel<ImageListViewmodel>()
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

        val viewModel = koinViewModel<ImageDetailsViewmodel>{
            parametersOf(it.arguments?.getString("imageId"))
        }
        val uiState = viewModel.uiState.collectAsState().value

        ImageDetailsScreen(
            uiState = uiState,
            onBackIconClick = navController::popBackStack
        )
    }
}

fun NavController.navigateToImageDetailsScreen(imageId: String) {
    navigate(NavigationRoutes.ImageDetailsScreen.route + "/" + imageId)
}
