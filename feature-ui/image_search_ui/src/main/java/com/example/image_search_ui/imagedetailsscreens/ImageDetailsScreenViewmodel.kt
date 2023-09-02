package com.example.image_search_ui.imagedetailsscreens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickr_search.repo.FlickrRepo
import com.example.image_search_ui.imagelistscreens.Photo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ImageDetailsScreenViewmodel(private val flickrRepo: FlickrRepo) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoDetailsUiState())
    val uiState = _uiState


    private fun getImageDetails() = viewModelScope.launch {
        val resource = flickrRepo.getImageDetails(uiState.value.imageId)

        val photoDetails = resource.photo.let { photo ->
            PhotoDetails(
                id = photo.id,
                url = "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                ownerIconUrl = "https://farm${photo.owner.iconfarm}.staticflickr.com/${photo.owner.iconserver}/buddyicons/${photo.owner.nsid}.jpg",
                ownerName = photo.owner.username,
                dateTaken = photo.dates.taken,
                ownerId = photo.owner.nsid,
            )
        }

        _uiState.value = uiState.value.copy(photoData = photoDetails)
        getImages()
    }

    private fun getImages() = viewModelScope.launch {
        val resource =
            flickrRepo.fetchImages(searchFor = null, userId = uiState.value.photoData.ownerId)

        val photosList = resource.photos.photo.map { photo ->
            Photo(
                id = photo.id,
                url = "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                ownerName = photo.ownername,
                ownerIconUrl = "https://farm${photo.iconfarm}.staticflickr.com/${photo.iconserver}/buddyicons/${photo.owner}.jpg",
            )
        }

        _uiState.value = uiState.value.copy(photoList = photosList)
    }


    fun setImagesId(id: String) {
        _uiState.value = uiState.value.copy(imageId = id)
        getImageDetails()
    }

}

//TODO move data classes into separate files
data class PhotoDetailsUiState(
    var imageId: String = "",
    var photoData: PhotoDetails = PhotoDetails(),
    var photoList: List<Photo> = emptyList(),
)

data class PhotoDetails(
    val id: String = "",
    val url: String = "",
    val ownerId: String = "",
    val ownerIconUrl: String = "",
    val ownerName: String = "",
    val dateTaken: String = "",
)