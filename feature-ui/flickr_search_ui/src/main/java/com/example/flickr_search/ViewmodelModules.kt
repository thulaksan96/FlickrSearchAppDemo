package com.example.flickr_search

import org.koin.dsl.module

val ViewmodelModules = module {

    single {
        ImageListScreenViewmodel(get())
    }

}