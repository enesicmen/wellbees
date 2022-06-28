package com.enes.wellbeeschallenge.data.repository

import PopularMoviesApiResponse
import com.enes.wellbeeschallenge.data.NetworkCallback
import com.enes.wellbeeschallenge.data.api.ApiService
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
}
