package com.velox.lazeir.utils.handler

import android.annotation.SuppressLint
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface HandlerInterface {
    fun <T, O> handleNetworkResponse(
        call: suspend () -> Response<T>, mapFun: (it: T) -> O
    ): Flow<NetworkResource<O>>


    fun <T> handleNetworkResponse(response:Response<T>): Flow<NetworkResource<T>>



    suspend fun <T> handleFlow(
        flow:Flow<NetworkResource<T>>,
        onLoading: suspend (it: Boolean) -> Unit,
        onFailure: suspend (it: String, errorObject: JSONObject, code: Int) -> Unit,
        onSuccess: suspend (it: T) -> Unit
    )



    @SuppressLint("LogNotTimber")
    fun <T> handleNetworkCall(call:Call<T>): Flow<NetworkResource<T>>


//cr velox
}