package com.enes.wellbeeschallenge.data.api.response

import com.enes.wellbeeschallenge.data.model.MovieCastModel

data class MovieCastApiResponse(
    val id: Int = 0,
    val cast: List<MovieCastModel> = listOf(),
)
