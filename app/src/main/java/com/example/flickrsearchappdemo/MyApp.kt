package com.example.flickrsearchappdemo

import android.app.Application
import com.example.flickr_search.di.flickrSearchModule
import com.example.image_search_ui.di.ViewmodelModules
import com.example.networking.di.networkingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                listOf(
                    networkingModule,
                    flickrSearchModule,
                    ViewmodelModules
                )
            )
        }
    }
}