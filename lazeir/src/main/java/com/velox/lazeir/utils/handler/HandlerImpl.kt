package com.velox.lazeir.utils.handler

import android.annotation.SuppressLint
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException

class HandlerImpl : HandlerInterface {
    /**
     * [handleNetworkResponse] handle the API response,
     * convert the dto response to domain response
     * extracting the error according to the error code
     * **/
    override fun <T, O> handleNetworkResponse(
        call: suspend () -> Response<T>, mapFun: (it: T) -> O
    ): Flow<NetworkResource<O>> {
        return flow {
            emit(NetworkResource.Loading(true))
            try {

                val response = call.invoke()
                if (response.isSuccessful) {
                    val data = response.body()?.let { mapFun(it) }
                    emit(NetworkResource.Success(data))
                } else {
                    val code = response.code()
                    val errorBody = response.errorBody()!!.string()
                    try {
                        val jObjError = JSONObject(errorBody)
                        emit(NetworkResource.Error("Response Error", jObjError, code))
                    } catch (e: Exception) {
                        e.message?.let { emit(NetworkResource.Error(it, null, code)) }
                    }
                }
            } catch (e: HttpException) {
                val errorBody = e.response()?.getJSONObject()
                val code = e.code()
                val message = e.message()
                e.message?.let { emit(NetworkResource.Error(message, errorBody, code)) }
            } catch (e: TimeoutException) {
                e.message?.let { emit(NetworkResource.Error("Time Out")) }
            } catch (e: SocketTimeoutException) {
                e.message?.let { emit(NetworkResource.Error("Time Out")) }
            } catch (e: IOException) {
                e.message?.let { emit(NetworkResource.Error(it)) }
            } catch (e: IllegalStateException) {
                e.message?.let { emit(NetworkResource.Error(it)) }
            } catch (e: NullPointerException) {
                e.message?.let { emit(NetworkResource.Error(it)) }
            } catch (e: JsonSyntaxException) {
                e.message?.let { emit(NetworkResource.Error(it)) }
            } catch (e: Exception) {
                e.message?.let { emit(NetworkResource.Error(it)) }
            }
            emit(NetworkResource.Loading(false))

        }.flowOn(Dispatchers.IO)
    }


    override fun <T> handleNetworkResponse(response: Response<T>): Flow<NetworkResource<T>> {
        return flow {
            emit(NetworkResource.Loading(isLoading = true))
            try {
                if (response.isSuccessful) {
                    emit(NetworkResource.Success(response.body()))
                } else {
                    val code = response.code()
                    val errorBody = response.errorBody()?.string()
                    try {
                        val jObjError = errorBody?.let { JSONObject(it) }
                        emit(NetworkResource.Error("Network Error", jObjError, code))
                    } catch (e: Exception) {
                        emit(NetworkResource.Error("UNKNOWN ERROR", code = code))
                    }
                }
            } catch (e: HttpException) {
                val errorBody = e.response()?.getJSONObject()
                val code = e.code()
                val message = e.message()
                e.message?.let { emit(NetworkResource.Error(message, errorBody, code)) }
            } catch (e: TimeoutException) {
                e.message?.let { emit(NetworkResource.Error("Time Out")) }
            } catch (e: SocketTimeoutException) {
                e.message?.let { emit(NetworkResource.Error("Time Out")) }
            } catch (e: IOException) {
                e.message?.let { emit(NetworkResource.Error(it)) }
            }  catch (e: IllegalStateException) {
                e.message?.let { emit(NetworkResource.Error(it)) }
            } catch (e: NullPointerException) {
                e.message?.let { emit(NetworkResource.Error(it)) }
            } catch (e: JsonSyntaxException) {
                e.message?.let { emit(NetworkResource.Error(it)) }
            } catch (e: Exception) {
                e.message?.let { emit(NetworkResource.Error(it)) }
            } finally {
                emit(NetworkResource.Loading(isLoading = false))
            }
            emit(NetworkResource.Loading(isLoading = false))
        }.flowOn(Dispatchers.IO)
    }


    /**
     * [handleFlow] takes the response from use case function as Resource<> with in Main Coroutine Scope
     * return the extracted response with in onLoading(),onFailure(),onSuccess()
     * Call within IO Scope
     * **/
    override suspend fun <T> handleFlow(
        flow: Flow<NetworkResource<T>>,
        onLoading: suspend (it: Boolean) -> Unit,
        onFailure: suspend (it: String, errorObject: JSONObject, code: Int) -> Unit,
        onSuccess: suspend (it: T) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            flow.collectLatest {
                when (it) {
                    is NetworkResource.Error -> {
                        CoroutineScope(Dispatchers.Main).launch {
                            onFailure.invoke(it.message!!, it.errorObject!!, it.code!!)
                        }
                    }

                    is NetworkResource.Loading -> {
                        CoroutineScope(Dispatchers.Main).launch {
                            onLoading.invoke(it.isLoading)
                        }
                    }

                    is NetworkResource.Success -> {
                        CoroutineScope(Dispatchers.Main).launch {
                            onSuccess.invoke(it.data!!)
                        }
                    }
                }
            }
        }
    }


    @SuppressLint("LogNotTimber")
    override fun <T> handleNetworkCall(call: Call<T>): Flow<NetworkResource<T>> {
        var code: Int?
        return flow {
            emit(NetworkResource.Loading(isLoading = true))
            try {
                val apiCall = call.awaitHandler()
                if (apiCall.isSuccessful) {
                    code = apiCall.code()
                    val body = apiCall.body()
                    emit(NetworkResource.Success(body))
                } else {
                    val errorBody = apiCall.getJSONObject()
                    code = apiCall.code()
                    val message = apiCall.message()
                    emit(NetworkResource.Error(message, errorBody, code))
                }
            } catch (e: HttpException) {
                val errorBody = e.response()?.getJSONObject()
                code = e.code()
                val message = e.message()
                e.message?.let { emit(NetworkResource.Error(message, errorBody, code)) }
            } catch (e: TimeoutException) {
                e.message?.let { emit(NetworkResource.Error("Time Out")) }
            } catch (e: SocketTimeoutException) {
                e.message?.let { emit(NetworkResource.Error("Time Out")) }
            } catch (e: IOException) {
                e.message?.let { emit(NetworkResource.Error(it)) }
            } catch (e: Exception) {
                e.message?.let { emit(NetworkResource.Error(it)) }
            } catch (e: JsonSyntaxException) {
                e.message?.let { emit(NetworkResource.Error(it)) }
            }
            emit(NetworkResource.Loading(isLoading = false))

        }.flowOn(Dispatchers.IO)
    }


//cr velox
}