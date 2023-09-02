package com.example.flickrsearchappdemo

import android.app.Application
import com.example.image_search_ui.di.ViewmodelModules
import com.example.image_search_ui.di.flickrSrcmod
import com.example.networking.di.networkingModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                listOf(
                    networkingModule,
                    flickrSrcmod,
                    ViewmodelModules
                )
            )
        }
    }
}