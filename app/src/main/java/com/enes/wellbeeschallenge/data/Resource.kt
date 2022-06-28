package com.enes.wellbeeschallenge.data

// wrapper class to store data and its stale like: Loading, Success or Error
sealed class Resource<T>(
    val data: T? = null,
    val error: String? = null
) {
    class Loading<T> : Resource<T>()
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}

val Resource<*>.isSucceeded
    get() = this is Resource.Success && data != null

val Resource<*>.isFailed
    get() = this is Resource.Error