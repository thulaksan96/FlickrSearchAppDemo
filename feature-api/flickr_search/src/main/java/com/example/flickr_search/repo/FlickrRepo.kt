package com.example.flickr_search.repo

import com.example.flickr_search.response.PhotoDetailResponse
import com.example.flickr_search.response.PhotosSearchResponse

interface FlickrRepo {

    suspend fun fetchImages(searchFor: String?, userId: String?): PhotosSearchResponse

    suspend fun getImageDetails(photoId: String): PhotoDetailResponse

}