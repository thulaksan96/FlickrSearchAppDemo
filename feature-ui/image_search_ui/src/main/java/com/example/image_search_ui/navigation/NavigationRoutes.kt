package com.example.image_search_ui.navigation

import kotlinx.serialization.Serializable

sealed class NavigationRoutes{
    @Serializable
    object SearchScreen : NavigationRoutes()
    @Serializable
    data class ImageDetailsScreen(val imageId: String) : NavigationRoutes()
}