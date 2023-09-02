package com.example.image_search_ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.image_search_ui.imagedetailsscreens.ImageDetailsScreen
import com.example.image_search_ui.imagedetailsscreens.ImageDetailsScreenViewmodel
import com.example.image_search_ui.imagelistscreens.ImageListScreen
import com.example.image_search_ui.imagelistscreens.ImageListScreenViewmodel
import org.koin.androidx.compose.getViewModel

fun NavGraphBuilder.imageListScreen(
    navigateToDetailsScreen: (String) -> Unit
) {
    composable(NavigationRoutes.SearchScreen.route) {

        val viewModel: ImageListScreenViewmodel = getViewModel()

        ImageListScreen(
            vm = viewModel,
            navigateToDetailsScreen = navigateToDetailsScreen
        )
    }
}

fun NavGraphBuilder.imageDetailsScreen(
    popBackStack: () -> Unit
) {
    composable(route = NavigationRoutes.ImageDetailsScreen.route + "/{imageId}",
        arguments = listOf(
            navArgument("imageId") { type = NavType.StringType }
        )) {

        val viewModel: ImageDetailsScreenViewmodel = getViewModel()

        LaunchedEffect(Unit) {
            viewModel.setImagesId(it.arguments?.getString("imageId") ?: "")
        }

        ImageDetailsScreen(
            vm = viewModel,
            onBackIconClick = popBackStack
        )
    }
}

fun NavController.navigateToImageDetailsScreen(imageId: String) {
    navigate(NavigationRoutes.ImageDetailsScreen.route + "/" + imageId)
}