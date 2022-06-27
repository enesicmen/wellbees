package com.enes.wellbeeschallenge.data.model

import com.google.gson.annotations.SerializedName

data class MovieCastModel(

    val id: Int = 0,

    val name: String = "",
    val character: String? = "",
    val order: Int = 0,
    var movieId: Int = 0,

    @SerializedName("profile_path")
    val imagePath: String? = "",

)
