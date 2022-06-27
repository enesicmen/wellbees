package com.enes.wellbeeschallenge.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

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

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<MovieModel> {
        override fun createFromParcel(parcel: Parcel): MovieModel {
            return MovieModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieModel?> {
            return arrayOfNulls(size)
        }
    }
}
