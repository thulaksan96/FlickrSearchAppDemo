package com.example.image_search_ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.image_search_ui.imagedetailsscreens.ImageDetailsScreen
import com.example.image_search_ui.imagedetailsscreens.ImageDetailsViewmodel
import com.example.image_search_ui.imagelistscreens.ImageListScreen
import com.example.image_search_ui.imagelistscreens.ImageListViewmodel
import com.example.image_search_ui.navigation.NavigationRoutes.ImageDetailsScreen
import com.example.image_search_ui.navigation.NavigationRoutes.SearchScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.imageListScreen(
    navController: NavController,
) {
    composable<SearchScreen> {

        val viewModel = koinViewModel<ImageListViewmodel>()
        val uiState = viewModel.uiState.collectAsState().value

        ImageListScreen(
            uiState = uiState,
            navigateToDetailsScreen = { imageId ->
                navController.navigate(ImageDetailsScreen(imageId))
            },
            changeSearchQuery = viewModel::changeSearchQuery,
            search = viewModel::search
        )
    }
}

fun NavGraphBuilder.imageDetailsScreen(
    navController: NavController,
) {
    composable<ImageDetailsScreen> {
        val args = it.toRoute<ImageDetailsScreen>()

        val viewModel = koinViewModel<ImageDetailsViewmodel> {
            parametersOf(args.imageId)
        }
        val uiState = viewModel.uiState.collectAsState().value

        ImageDetailsScreen(
            uiState = uiState,
            onBackIconClick = navController::popBackStack
        )
    }
}

