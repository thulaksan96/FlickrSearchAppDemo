package com.example.flickr_search.repo

import com.example.flickr_search.response.PhotoDetailResponse
import com.example.flickr_search.response.PhotosSearchResponse
import com.example.networking.model.Resource

interface FlickrRepo {
    suspend fun fetchImages(searchFor: String?, userId: String?): Resource<PhotosSearchResponse>
    suspend fun getImageDetails(photoId: String): Resource<PhotoDetailResponse>
}