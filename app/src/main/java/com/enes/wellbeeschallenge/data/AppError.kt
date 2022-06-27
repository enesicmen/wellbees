package com.enes.wellbeeschallenge.data

import com.enes.wellbeeschallenge.data.api.ErrorType

/**
 * Application-wide error model
 */
data class AppError(
    var code: Int = 0,
    val message: String = "",
    val type: ErrorType = ErrorType.UNDEFINED
)
