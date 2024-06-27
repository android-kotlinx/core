package com.androidx.core.utils.network_handler

import android.util.Log
import com.androidx.core.awaitHandler
import com.androidx.core.getJSONObject
import com.androidx.core.outlet.pub.RetrofitResource
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException


    fun <T, O> handleNetworkResponse(
        call: suspend () -> Response<T>,
        mapFun: (it: T) -> O
    ): Flow<RetrofitResource<O>> {
        return flow {
            emit(RetrofitResource.Loading(true))
            try {

                val response = call.invoke()
                if (response.isSuccessful) {
                    val data = response.body()?.let { mapFun(it) }
                    emit(RetrofitResource.Success(data))
                } else {
                    val code = response.code()
                    val errorBody = response.errorBody()?.string()
                    try {
                        val jObjError = errorBody?.let { JSONObject(it) }
                        emit(RetrofitResource.Error("Response Error", jObjError, code))
                    } catch (e: Exception) {
                        e.message?.let { emit(RetrofitResource.Error(it, null, code)) }
                    }
                }
            } catch (e: HttpException) {
                val errorBody = e.response()?.getJSONObject()
                val code = e.code()
                val message = e.message()
                e.message?.let { emit(RetrofitResource.Error(message, errorBody, code)) }
            } catch (e: TimeoutException) {
                e.message?.let { emit(RetrofitResource.Error("Time Out")) }
            } catch (e: SocketTimeoutException) {
                e.message?.let { emit(RetrofitResource.Error("Time Out")) }
            } catch (e: IOException) {
                e.message?.let { emit(RetrofitResource.Error(it)) }
            } catch (e: IllegalStateException) {
                e.message?.let { emit(RetrofitResource.Error(it)) }
            } catch (e: NullPointerException) {
                e.message?.let { emit(RetrofitResource.Error(it)) }
            } catch (e: JsonSyntaxException) {
                e.message?.let { emit(RetrofitResource.Error(it)) }
            } catch (e: Exception) {
                e.message?.let { emit(RetrofitResource.Error(it)) }
            }
            emit(RetrofitResource.Loading(false))

        }.flowOn(Dispatchers.IO)
    }

    fun <T> handleNetworkResponse(response: Response<T>): Flow<RetrofitResource<T>>  {
        return flow {
            emit(RetrofitResource.Loading(isLoading = true))
            try {
                if (response.isSuccessful) {
                    emit(RetrofitResource.Success(response.body()))
                } else {
                    val code = response.code()
                    val errorBody = response.errorBody()?.string()
                    try {
                        val jObjError = errorBody?.let { JSONObject(it) }
                        emit(RetrofitResource.Error("Network Error", jObjError, code))
                    } catch (e: Exception) {
                        emit(RetrofitResource.Error("UNKNOWN ERROR", code = code))
                    }
                }
            } catch (e: HttpException) {
                val errorBody = e.response()?.getJSONObject()
                val code = e.code()
                val message = e.message()
                e.message?.let { emit(RetrofitResource.Error(message, errorBody, code)) }
            } catch (e: TimeoutException) {
                e.message?.let { emit(RetrofitResource.Error("Time Out")) }
            } catch (e: SocketTimeoutException) {
                e.message?.let { emit(RetrofitResource.Error("Time Out")) }
            } catch (e: IOException) {
                e.message?.let { emit(RetrofitResource.Error(it)) }
            }  catch (e: IllegalStateException) {
                e.message?.let { emit(RetrofitResource.Error(it)) }
            } catch (e: NullPointerException) {
                e.message?.let { emit(RetrofitResource.Error(it)) }
            } catch (e: JsonSyntaxException) {
                e.message?.let { emit(RetrofitResource.Error(it)) }
            } catch (e: Exception) {
                e.message?.let { emit(RetrofitResource.Error(it)) }
            } finally {
                emit(RetrofitResource.Loading(isLoading = false))
            }
            emit(RetrofitResource.Loading(isLoading = false))
        }.flowOn(Dispatchers.IO)
    }

    fun <T> handleNetworkCall(call: Call<T>): Flow<RetrofitResource<T>>  {
        var code: Int?
        return flow {
            emit(RetrofitResource.Loading(isLoading = true))
            try {
                val apiCall = call.awaitHandler()
                if (apiCall.isSuccessful) {
                    code = apiCall.code()
                    val body = apiCall.body()
                    emit(RetrofitResource.Success(body))
                } else {
                    val errorBody = apiCall.getJSONObject()
                    code = apiCall.code()
                    val message = apiCall.message()
                    emit(RetrofitResource.Error(message, errorBody, code))
                }
            } catch (e: HttpException) {
                val errorBody = e.response()?.getJSONObject()
                code = e.code()
                val message = e.message()
                e.message?.let { emit(RetrofitResource.Error(message, errorBody, code)) }
            } catch (e: TimeoutException) {
                e.message?.let { emit(RetrofitResource.Error("Time Out")) }
            } catch (e: SocketTimeoutException) {
                e.message?.let { emit(RetrofitResource.Error("Time Out")) }
            } catch (e: IOException) {
                e.message?.let { emit(RetrofitResource.Error(it)) }
            } catch (e: Exception) {
                e.message?.let { emit(RetrofitResource.Error(it)) }
            } catch (e: JsonSyntaxException) {
                e.message?.let { emit(RetrofitResource.Error(it)) }
            }
            emit(RetrofitResource.Loading(isLoading = false))

        }.flowOn(Dispatchers.IO)
    }



