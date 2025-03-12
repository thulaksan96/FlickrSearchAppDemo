package com.example.image_search_ui.imagedetailsscreens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickr_search.repo.FlickrRepo
import com.example.image_search_ui.imagelistscreens.Photo
import com.example.networking.model.onError
import com.example.networking.model.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ImageDetailsViewmodel(
    private val imageId: String,
    private val flickrRepo: FlickrRepo,
    private val uiMapper: ImageDetailMapper,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoDetailsUiState())
    val uiState = _uiState

    init {
        _uiState.update { it.copy(imageId = imageId, loading = true) }
        getImageDetails()
    }

    private fun getImageDetails() = viewModelScope.launch {
        flickrRepo.getImageDetails(uiState.value.imageId).onSuccess { data ->
            val photoDetails = uiMapper.mapPhotoDetails(data.photo)
            _uiState.update { it.copy(photoData = photoDetails, loading = false) }
            getImages()
        }.onError {
            _uiState.update { it.copy(loadDetailFailed = true) }
        }
    }

    private fun getImages() = viewModelScope.launch {
        val resource = flickrRepo.fetchImages(
            searchFor = null, userId = uiState.value.photoData.ownerId
        )
        resource.onSuccess { data ->
            val photosList = data.photos.photo.map { photo ->
                uiMapper.mapPhoto(photo)
            }
            _uiState.update { it.copy(photoList = photosList) }
        }.onError {
            _uiState.update { it.copy(loadImagesFailed = true) }
        }
    }
}

internal data class PhotoDetailsUiState(
    val loading: Boolean = false,
    val imageId: String = "",
    val photoData: PhotoDetails = PhotoDetails(),
    val photoList: List<Photo> = emptyList(),
    val loadDetailFailed: Boolean = false,
    val loadImagesFailed: Boolean = false,
)

internal data class PhotoDetails(
    val id: String = "",
    val url: String = "",
    val ownerId: String = "",
    val ownerIconUrl: String = "",
    val ownerName: String = "",
    val dateTaken: String = "",
)