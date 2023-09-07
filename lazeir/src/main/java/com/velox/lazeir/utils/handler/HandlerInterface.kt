package com.velox.lazeir.utils.handler

import android.annotation.SuppressLint
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

interface HandlerInterface {
    fun <T, O> handleNetworkResponse(
        call: suspend () -> Response<T>, mapFun: (it: T) -> O
    ): Flow<NetworkResource<O>>


    fun <T> handleNetworkResponse(response:Response<T>): Flow<NetworkResource<T>>



    fun <T> handleFlow(
        flow:Flow<NetworkResource<T>>,
        onLoading:  (it: Boolean) -> Unit,
        onFailure:  (it: String, errorObject: JSONObject, code: Int) -> Unit,
        onSuccess:  (it: T) -> Unit
    )



    @SuppressLint("LogNotTimber")
    fun <T> handleNetworkCall(call:Call<T>): Flow<NetworkResource<T>>


//cr velox
}