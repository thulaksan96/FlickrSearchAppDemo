package com.example.networking.model

import com.example.networking.model.Resource.Success
import retrofit2.Response

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val responseCode: Int?) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}

suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Resource<T> {
    return try {
        val response = call.invoke()
        if (response.isSuccessful) {
            Success(response.body()!!)
        } else {
            Resource.Error(response.code())
        }
    } catch (e: Exception) {
        Resource.Error(null)
    }
}

inline fun <T> Resource<T>.onSuccess(block: (result: T) -> Unit): Resource<T> {
    if (this is Success) {
        block(data)
    }
    return this
}

inline fun <T> Resource<T>.onError(block: () -> Unit): Resource<T> {
    if (this is Resource.Error) {
        block()
    }
    return this
}
