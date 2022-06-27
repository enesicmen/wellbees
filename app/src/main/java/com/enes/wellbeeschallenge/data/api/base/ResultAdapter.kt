package com.enes.wellbeeschallenge.data.api.base

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type
import com.enes.wellbeeschallenge.data.result.Result


class ResultAdapter(
    private val type: Type
) : CallAdapter<Type, Call<Result<Type>>> {
    override fun responseType() = type
    override fun adapt(call: Call<Type>): Call<Result<Type>> = ResultCall(call)
}
