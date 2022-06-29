package com.enes.wellbeeschallenge.data.repository

import com.enes.wellbeeschallenge.data.NetworkCallback
import com.enes.wellbeeschallenge.data.api.ApiService
import com.enes.wellbeeschallenge.data.api.response.PersonApiResponse
import com.enes.wellbeeschallenge.data.model.PersonModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getPersonDetail(personId: Int, callback: NetworkCallback<List<PersonModel>>) {

        val call: Call<PersonApiResponse> = apiService.getPersonDetails(personId)
        call.enqueue(object : Callback<PersonApiResponse> {
            override fun onResponse(
                call: Call<PersonApiResponse>,
                response: Response<PersonApiResponse>
            ) {
                if (response.isSuccessful) {
                    val personApiResponse = response.body()!!
                    callback.onSuccess(data = personApiResponse.person)
                } else {
                    callback.onError(message = response.message())
                }
            }

            override fun onFailure(call: Call<PersonApiResponse>, t: Throwable) {
                callback.onError(message = t.message ?: "")
            }
        })
    }
}
