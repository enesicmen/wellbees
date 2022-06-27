package com.enes.wellbeeschallenge.data.result

import com.enes.wellbeeschallenge.data.AppError
import com.enes.wellbeeschallenge.data.api.ErrorType

/**
 * Loads data from Network, converts it to a [Result] and returns
 */
abstract class NetworkResult<ApiResponseType, ResultType> {

    /**
     * Override this to load data from Network.
     * @return a [Result].
     */
    abstract suspend fun load(): Result<ApiResponseType>

    /**
     * Override this to get needed data from [ApiResponseType]
     * @param apiResponse the result of the [load]
     * @return a [ResultType].
     */
    abstract suspend fun getResult(apiResponse: ApiResponseType): ResultType?

    /**
     * Executes [load] asynchronously
     * Executes [getResult] to get needed data from [ApiResponseType]
     * @return a [Result].
     */
    suspend fun execute(): Result<ResultType> {
        load().apply {
            return when {
                this.succeeded ->
                    getResult(apiResponse = data!!)?.let {
                        Result.Success(it)
                    } ?: run {
                        val error = AppError(type = ErrorType.UNEXPECTED_RESPONSE, message = "Unexpected API response")
                        Result.Error(appError = error)
                    }
                else -> this as Result.Error
            }
        }
    }
}
