package com.example.flickr_search.di

import com.example.flickr_search.repo.FlickrRepo
import com.example.flickr_search.repo.FlickrRepoImpl
import com.example.flickr_search.services.SearchImageService
import com.example.networking.Networking
import org.koin.dsl.module

val flickrSearchModule = module {

    single {
        createService(
            networkServiceClass = SearchImageService::class.java,
            networking = get()
        )
    }


    single<FlickrRepo> {
        FlickrRepoImpl(get())
    }

}

internal fun <T> createService(
    networkServiceClass: Class<T>,
    networking: Networking
): T = networking.createService(networkServiceClass)
