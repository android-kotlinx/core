package com.androidx.core.domain

import com.androidx.core.outlet.pub.RetrofitResource
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response


interface HandlerInterface {


    fun <T, O> handleNetworkResponse(
        call: suspend () -> Response<T>, mapFun: (it: T) -> O
    ): Flow<RetrofitResource<O>>

    fun <T> handleNetworkResponse(response: Response<T>): Flow<RetrofitResource<T>>

    fun <T> handleNetworkCall(call: Call<T>): Flow<RetrofitResource<T>>

}




