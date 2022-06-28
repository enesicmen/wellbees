package com.enes.wellbeeschallenge.data.api

import PopularMoviesApiResponse
import com.enes.wellbeeschallenge.data.api.response.MovieCastApiResponse
import com.enes.wellbeeschallenge.data.model.PersonModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovies(): Call<PopularMoviesApiResponse>

    @GET("movie/{movieId}/credits")
    fun getCastOfAMovie(@Path("movieId") movieId: Int): Call<MovieCastApiResponse>

    @GET("person/{personId}")
    fun getPersonDetails(@Path("personId") personId: Int): Call<PersonModel>
}
