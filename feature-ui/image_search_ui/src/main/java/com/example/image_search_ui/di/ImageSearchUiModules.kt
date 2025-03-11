package com.example.image_search_ui.di

import com.example.image_search_ui.imagedetailsscreens.ImageDetailMapper
import com.example.image_search_ui.imagedetailsscreens.ImageDetailsViewmodel
import com.example.image_search_ui.imagelistscreens.ImageListViewmodel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val ImageSearchUiModules = module {
    viewModelOf(::ImageListViewmodel)
    viewModelOf(::ImageDetailsViewmodel)
    factoryOf(::ImageDetailMapper)
}
