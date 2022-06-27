package com.enes.wellbeeschallenge.data.model

import com.google.gson.annotations.SerializedName

data class PersonModel(
    val id: Int = 0,

    val name: String? = "",
    val biography: String? = "",

    @SerializedName("profile_path")
    val imagePath: String? = "",

)
