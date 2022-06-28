package com.enes.wellbeeschallenge.data

interface NetworkCallback<T> {
    fun onSuccess(data: T)
    fun onError(message: String)
}