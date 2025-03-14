package com.example.flickr_search.repo

import com.example.flickr_search.response.PhotoDetailResponse
import com.example.flickr_search.response.PhotosSearchResponse
import com.example.flickr_search.services.SearchImageService
import com.example.networking.model.Resource
import com.example.networking.model.apiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlickrRepoImpl(
    private val searchImageService: SearchImageService,
) : FlickrRepo {

    override suspend fun fetchImages(
        searchFor: String?,
        userId: String?,
    ): Resource<PhotosSearchResponse> =
        withContext(
            Dispatchers.IO
        ) {
            apiCall { searchImageService.fetchImages(searchFor = searchFor, userId = userId) }
        }

    override suspend fun getImageDetails(photoId: String): Resource<PhotoDetailResponse> =
        withContext(
            Dispatchers.IO
        ) {
            apiCall { searchImageService.getImageDetails(photoId) }
        }
}