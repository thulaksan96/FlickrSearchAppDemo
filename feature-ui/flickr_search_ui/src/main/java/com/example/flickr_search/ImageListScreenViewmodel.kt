package com.example.flickr_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickr_search.repo.FlickrRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ImageListScreenViewmodel(
    private val flickrRepo: FlickrRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoUiState())
    val uiState = _uiState

    init {
        getImages(searchFor = "kittens")
    }

    private fun getImages(searchFor: String) = viewModelScope.launch {
        val resource = flickrRepo.fetchImages(searchFor)

        val photosList = resource.photos.photo.map { photo ->
            Photo(
                id = photo.id,
                url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                title = photo.title
            )
        }

        _uiState.value = uiState.value.copy(photoList = photosList)
    }

    fun changeSearchQuery(query: String) {
        _uiState.value = uiState.value.copy(searchQuery = query)
    }

    fun search() {
        getImages(searchFor = uiState.value.searchQuery)
    }

}

data class Photo(
    val id: String,
    val url: String,
    val title: String
)

data class PhotoUiState(
    var searchQuery: String = "",
    var photoList: List<Photo> = emptyList(),
)