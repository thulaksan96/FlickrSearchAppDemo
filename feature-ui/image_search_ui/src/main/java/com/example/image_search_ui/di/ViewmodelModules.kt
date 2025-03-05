package com.example.image_search_ui.di

import com.example.image_search_ui.imagedetailsscreens.ImageDetailsScreenViewmodel
import com.example.image_search_ui.imagelistscreens.ImageListScreenViewmodel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewmodelModules = module {
    viewModel { ImageListScreenViewmodel(get()) }
    viewModel { ImageDetailsScreenViewmodel(get()) }
}
