package com.example.flickr_search.services

import com.example.flickr_search.response.PhotoDetailResponse
import com.example.flickr_search.response.PhotosSearchResponse
import com.example.networking.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchImageService {

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&extras=owner_name%2Cicon_server%2Cicon_farm")
    suspend fun fetchImages(
        @Query("text") searchFor: String?,
        @Query("user_id") userId: String?,
        @Query("api_key") apiKey: String = API_KEY,
    ): PhotosSearchResponse

    @GET("?method=flickr.photos.getInfo&format=json&nojsoncallback=1")
    suspend fun getImageDetails(
        @Query("photo_id") photoId: String,
        @Query("api_key") apiKey: String = API_KEY,
    ): PhotoDetailResponse


}