package com.example.networking

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_KEY = "3e7cc266ae2b0e0d78e279ce8e361736"
const val FLIKR_BASE_URL = "https://api.flickr.com/services/rest/"

class NetworkingImpl() : Networking {
    override fun <T> createService(clazz: Class<T>): T = Retrofit.Builder()
        .baseUrl(FLIKR_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(clazz)
}