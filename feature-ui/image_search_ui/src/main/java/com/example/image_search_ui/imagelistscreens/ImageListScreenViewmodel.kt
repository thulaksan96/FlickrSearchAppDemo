package com.example.image_search_ui.imagelistscreens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickr_search.repo.FlickrRepo
import com.example.networking.model.onError
import com.example.networking.model.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ImageListScreenViewmodel(
    private val flickrRepo: FlickrRepo,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoUiState())
    val uiState = _uiState

    init {
        getImages()
    }

    private fun getImages() = viewModelScope.launch {
        val resource =
            flickrRepo.fetchImages(uiState.value.searchQuery.ifBlank { "dog" }, userId = null)

        resource.onSuccess { data ->
            val photosList = data.photos.photo.map { photo ->
                Photo(
                    id = photo.id,
                    url = "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    ownerName = photo.ownername,
                    ownerIconUrl = "https://farm${photo.iconfarm}.staticflickr.com/${photo.iconserver}/buddyicons/${photo.owner}.jpg",
                )
            }
            _uiState.value = uiState.value.copy(photoList = photosList)
        }.onError {
            //TODO handle error
        }
    }

    fun changeSearchQuery(query: String) {
        _uiState.value = uiState.value.copy(searchQuery = query)
    }

    fun search() {
        getImages()
    }

}

data class Photo(
    val id: String,
    val url: String,
    val ownerName: String,
    val ownerIconUrl: String = "",
)

data class PhotoUiState(
    var searchQuery: String = "",
    var photoList: List<Photo> = emptyList(),
)