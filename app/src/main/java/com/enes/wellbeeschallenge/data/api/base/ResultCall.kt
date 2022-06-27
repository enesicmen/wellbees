package com.enes.wellbeeschallenge.data.api.base

import com.enes.wellbeeschallenge.data.AppError
import com.enes.wellbeeschallenge.data.api.ApiError
import com.enes.wellbeeschallenge.data.api.ErrorType
import com.google.gson.Gson
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.enes.wellbeeschallenge.data.result.Result
import java.io.IOException

class ResultCall<T>(proxy: Call<T>) : CallDelegate<T, Result<T>>(proxy) {
    override fun enqueueImpl(callback: Callback<Result<T>>) = proxy.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            val code = response.code()
            val result = if (code in 200 until 300) {
                val body = response.body()
                val successResult: Result<T> = Result.Success(body!!)
                successResult
            } else {
                Result.Error(appError = createAppError(response = response))
            }
            callback.onResponse(this@ResultCall, Response.success(result))
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            val result = Result.Error(createAppError(throwable = t))
            callback.onResponse(this@ResultCall, Response.success(result))
        }
    })

    override fun cloneImpl() = ResultCall(proxy.clone())

    override fun timeout(): Timeout = Timeout.NONE

    private fun createAppError(
        response: Response<T>? = null,
        throwable: Throwable? = null
    ): AppError {
        response?.errorBody()?.let {
            return try {
                val apiError = Gson().fromJson(it.string(), ApiError::class.java)
                AppError(
                    type = ErrorType.HTTP,
                    code = apiError.code ?: 0,
                    message = apiError.message ?: "Unknown API error."
                )
            } catch (exception: Exception) {
                AppError(type = ErrorType.HTTP, message = "Unknown API error.")
            }
        }

        throwable?.let {
            return if (throwable is IOException) {
                AppError(
                    type = ErrorType.CONNECTION,
                    code = 500,
                    message = "Could not connected to internet"
                )
            } else {
                AppError(
                    type = ErrorType.UNEXPECTED_RESPONSE,
                    code = 500,
                    message = "Unexcepted response from API: ${throwable.localizedMessage}"
                )
            }
        }

        return AppError(type = ErrorType.HTTP, message = "Unknown API error.")
    }
}
