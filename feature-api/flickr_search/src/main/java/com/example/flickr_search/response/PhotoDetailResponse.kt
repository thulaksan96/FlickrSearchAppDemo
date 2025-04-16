package com.example.flickr_search.response

data class PhotoDetailResponse(
    val photo: Photo,
)

data class Photo(
    val id: String,
    val owner: Owner,
    val secret: String,
    val server: String,
    val farm: Int,
    val dates: Dates,
)

data class Dates(
    val taken: String,
)

data class Owner(
    val nsid: String,
    val username: String,
    val iconserver: String,
    val iconfarm: Long,
)