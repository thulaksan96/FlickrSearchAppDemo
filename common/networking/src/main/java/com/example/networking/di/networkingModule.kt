package com.example.networking.di

import com.example.networking.Networking
import com.example.networking.NetworkingImpl
import org.koin.dsl.module

val networkingModule = module {
    single<Networking> {
        NetworkingImpl()
    }
}
