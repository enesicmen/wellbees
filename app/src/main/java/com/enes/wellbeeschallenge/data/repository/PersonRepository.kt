package com.enes.wellbeeschallenge.data.repository

import com.enes.wellbeeschallenge.data.NetworkCallback
import com.enes.wellbeeschallenge.data.api.ApiService
import com.enes.wellbeeschallenge.data.model.PersonModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getPersonDetail(personId: Int, callback: NetworkCallback<PersonModel>) {

        val call: Call<PersonModel> = apiService.getPersonDetails(personId)
        call.enqueue(object : Callback<PersonModel> {
            override fun onResponse(
                call: Call<PersonModel>,
                response: Response<PersonModel>
            ) {
                if (response.isSuccessful) {
                    callback.onSuccess(data = response.body()!!)
                } else {
                    callback.onError(message = response.message())
                }
            }

            override fun onFailure(call: Call<PersonModel>, t: Throwable) {
                callback.onError(message = t.message ?: "")
            }
        })
    }
}
