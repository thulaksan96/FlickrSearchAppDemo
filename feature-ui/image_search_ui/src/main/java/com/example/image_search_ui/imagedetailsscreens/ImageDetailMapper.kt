package com.example.image_search_ui.imagedetailsscreens

import com.example.flickr_search.response.Photo
import com.example.flickr_search.response.PhotoResponse

internal class ImageDetailMapper {

    fun mapPhotoDetails(data: Photo) = PhotoDetails(
        id = data.id,
        url = "https://live.staticflickr.com/${data.server}/${data.id}_${data.secret}.jpg",
        ownerIconUrl = "https://farm${data.owner.iconfarm}.staticflickr.com/${data.owner.iconserver}/buddyicons/${data.owner.nsid}.jpg",
        ownerName = data.owner.username,
        dateTaken = data.dates.taken,
        ownerId = data.owner.nsid,
    )

    fun mapPhoto(data: PhotoResponse) = com.example.image_search_ui.imagelistscreens.Photo(
        id = data.id,
        url = "https://live.staticflickr.com/${data.server}/${data.id}_${data.secret}.jpg",
        ownerName = data.ownername,
        ownerIconUrl = "https://farm${data.iconfarm}.staticflickr.com/${data.iconserver}/buddyicons/${data.owner}.jpg",
    )
}