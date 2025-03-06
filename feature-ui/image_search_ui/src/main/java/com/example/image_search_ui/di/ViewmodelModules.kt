package com.example.image_search_ui.di

import com.example.image_search_ui.imagedetailsscreens.ImageDetailsViewmodel
import com.example.image_search_ui.imagelistscreens.ImageListViewmodel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewmodelModules = module {
    viewModel { ImageListViewmodel(get()) }
    viewModel { ImageDetailsViewmodel(get()) }
}
