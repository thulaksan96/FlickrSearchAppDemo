package com.example.flickr_search.repo

import com.example.flickr_search.response.PhotoDetailResponse
import com.example.flickr_search.response.PhotosSearchResponse
import com.example.flickr_search.services.SearchImageService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlickrRepoImpl(
    private val searchImageService: SearchImageService,
) : FlickrRepo {

    override suspend fun fetchImages(searchFor: String?, userId: String?): PhotosSearchResponse =
        withContext(
            Dispatchers.IO
        ) {

            //TODO add try catch and error handling
            searchImageService.fetchImages(searchFor = searchFor, userId = userId)
        }

    override suspend fun getImageDetails(photoId: String): PhotoDetailResponse = withContext(
        Dispatchers.IO
    ) {
        //TODO add try catch and error handling
        searchImageService.getImageDetails(photoId)
    }
}