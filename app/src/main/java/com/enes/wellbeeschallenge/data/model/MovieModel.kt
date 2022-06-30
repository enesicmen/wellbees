package com.enes.wellbeeschallenge.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(

    val id: Int = -1,

    var adult: Boolean? = false,

    @SerializedName("poster_path")
    val posterImagePath: String? = "",

    @SerializedName("backdrop_path")
    val backdropImagePath: String? = "",

    @SerializedName("title")
    val title: String? = "",

    val overview: String? = "",

    @SerializedName("vote_average")
    val average: Double? = 0.0,

    @SerializedName("vote_count")
    val voteCount: Double? = 0.0,

    @SerializedName("release_date")
    val releaseDate: String? = "",

    val popularity: Double? = 0.0,

) : Parcelable
