package com.enes.wellbeeschallenge.data.repository

import PopularMoviesApiResponse
import com.enes.wellbeeschallenge.data.NetworkCallback
import com.enes.wellbeeschallenge.data.api.ApiService
import com.enes.wellbeeschallenge.data.api.response.MovieCastApiResponse
import com.enes.wellbeeschallenge.data.model.MovieCastModel
import com.enes.wellbeeschallenge.data.model.MovieModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getPopularMovies(callback: NetworkCallback<List<MovieModel>>) {

        val call: Call<PopularMoviesApiResponse> = apiService.getPopularMovies()
        call.enqueue(object : Callback<PopularMoviesApiResponse> {
            override fun onResponse(
                call: Call<PopularMoviesApiResponse>,
                response: Response<PopularMoviesApiResponse>
            ) {
                if (response.isSuccessful) {
                    val popularMoviesApiResponse = response.body()!!
                    callback.onSuccess(data = popularMoviesApiResponse.results)
                } else {
                    callback.onError(message = response.message())
                }
            }

            override fun onFailure(call: Call<PopularMoviesApiResponse>, t: Throwable) {
                callback.onError(message = t.message ?: "")
            }
        })
    }

    fun getMovieDetail(movieId: Int, callback: NetworkCallback<List<MovieCastModel>>) {

        val call: Call<MovieCastApiResponse> = apiService.getCastOfAMovie(movieId)
        call.enqueue(object : Callback<MovieCastApiResponse> {
            override fun onResponse(
                call: Call<MovieCastApiResponse>,
                response: Response<MovieCastApiResponse>
            ) {
                if (response.isSuccessful) {
                    val moviesCastApiResponse = response.body()!!
                    callback.onSuccess(data = moviesCastApiResponse.cast)
                } else {
                    callback.onError(message = response.message())
                }
            }
            override fun onFailure(call: Call<MovieCastApiResponse>, t: Throwable) {
                callback.onError(message = t.message ?: "")
            }
        })
    }
}
