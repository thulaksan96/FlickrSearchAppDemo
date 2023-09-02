package com.example.flickr_search.response

data class PhotoDetailResponse(
    val photo: Photo,
    val stat: String
)

data class Photo(
    val id: String,
    val owner: Owner,
    val secret: String,
    val server: String,
    val farm: Int,

//    val id: String,
//    val secret: String,
//    val server: String,
//    val farm: Long,
//
//    val dateuploaded: String,
//    val isfavorite: Long,
//    val license: String,
//
//
//    val rotation: Long,
//    val originalsecret: String,
//    val originalformat: String,
//    val owner: Owner,
////    val title: Comments,
////    val description: Comments,
//    val visibility: Visibility,
    val dates: Dates,
//    val views: String,
//    val editability: Editability,
//    val publiceditability: Editability,
//    val usage: Usage,
//    val notes: Notes,
//    val people: People,
//    val tags: Tags,
//    val urls: Urls,
//    val media: String
)

data class Dates(
    val taken: String,
)

data class Editability(
    val cancomment: Long,
    val canaddmeta: Long
)

data class Notes(
    val note: List<Any?>
)

data class Owner(
    val nsid: String,
    val username: String,
    val iconserver: String,
    val iconfarm: Long,
)


data class People(
    val haspeople: Long
)

data class Tags(
    val tag: List<Any?>
)

data class Urls(
    val url: List<URL>
)

data class URL(
    val type: String,
)

data class Usage(
    val candownload: Long,
    val canblog: Long,
    val canprint: Long,
    val canshare: Long
)

data class Visibility(
    val ispublic: Long,
    val isfriend: Long,
    val isfamily: Long
)