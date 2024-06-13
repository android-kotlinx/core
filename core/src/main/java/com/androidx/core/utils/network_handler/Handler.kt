package com.androidx.core.utils.network_handler

import android.util.Log
import com.androidx.core.awaitHandler
import com.androidx.core.domain.HandlerInterface
import com.androidx.core.getJSONObject
import com.google.gson.JsonSyntaxException
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException

internal class Handler:HandlerInterface {
    override fun <T, O> handleNetworkResponse(
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

    override fun <T> handleNetworkResponse(response: Response<T>): Flow<RetrofitResource<T>>  {
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

    override fun <T> handleNetworkCall(call: Call<T>): Flow<RetrofitResource<T>>  {
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
}

internal inline fun <T> HandlerInterface.handleFlowKtor(
    flow: Flow<KtorResource<T>>,
    crossinline onLoading: suspend (it: Boolean) -> Unit,
    crossinline onFailure: suspend (it: String?, errorObject: JsonObject?, code: Int?) -> Unit,
    crossinline onSuccess: suspend (it: T?) -> Unit,
) {
    CoroutineScope(Dispatchers.IO).launch {
        onLoading(true)
        flow.collectLatest {
            when (it) {
                is KtorResource.Error -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        onFailure.invoke(it.message, it.errorObject, it.code)
                    }
                }

                is KtorResource.Loading -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        onLoading.invoke(it.isLoading)
                    }
                }

                is KtorResource.Success -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        onSuccess.invoke(it.data)
                    }
                }
            }
        }
    }
}

inline fun <reified T> HandlerInterface.handleNetworkResponse(crossinline call: suspend () -> HttpResponse): Flow<KtorResource<T>> {
    return flow {
        emit(KtorResource.Loading(isLoading = true))
        try {
            val response = call.invoke()
            Log.i("HandleNetworkResponse", "$response \n ${response.body<T>()}")
            val status = response.status.value
            if (status == 200) {
                emit(KtorResource.Success(response.body()))
            } else {
                emit(KtorResource.Error("Call Exception", response.body(), status))
            }

        } catch (e: ClientRequestException) {
            emit(
                KtorResource.Error(
                    "ClientRequestException",
                    e.response.body(),
                    e.response.status.value
                )
            )
        } catch (e: ServerResponseException) {
            emit(
                KtorResource.Error(
                    "ServerResponseException",
                    e.response.body(),
                    e.response.status.value
                )
            )
        } catch (e: RedirectResponseException) {
            emit(
                KtorResource.Error(
                    "RedirectResponseException",
                    e.response.body(),
                    e.response.status.value
                )
            )
        } catch (e: ResponseException) {
            emit(
                KtorResource.Error(
                    "ResponseException",
                    e.response.body(),
                    e.response.status.value
                )
            )
        } catch (e: SecurityException) {

            emit(KtorResource.Error("Connection Timeout"))
        } catch (e: SocketTimeoutException) {

            emit(KtorResource.Error("Socket Timeout"))
        } catch (e: IOException) {

            emit(KtorResource.Error(e.message ?: "Unknown IO Error"))
        } catch (e: TimeoutException) {
            emit(KtorResource.Error(e.message ?: "Unknown TimeoutException Error"))
        } catch (e: Exception) {
            emit(KtorResource.Error(e.message ?: "Unknown Error"))
        } catch (e: IllegalArgumentException) {
            emit(KtorResource.Error(e.message ?: "IllegalArgumentException"))
        } catch (e: UnsupportedOperationException) {
            emit(KtorResource.Error(e.message ?: "UnsupportedOperationException"))
        }

        emit(KtorResource.Loading(isLoading = false))
    }
}

internal inline fun <T> HandlerInterface.handleFlow(
    flow: Flow<RetrofitResource<T>>,
    crossinline onLoading: suspend (it: Boolean) -> Unit,
    crossinline onFailure: suspend (it: String?, errorObject: JSONObject?, code: Int?) -> Unit,
    crossinline onSuccess: suspend (it: T?) -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        onLoading(true)
        flow.collectLatest {
            when (it) {
                is RetrofitResource.Error -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        onFailure.invoke(it.message, it.errorObject, it.code)
                    }
                }

                is RetrofitResource.Loading -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        onLoading.invoke(it.isLoading)
                    }
                }

                is RetrofitResource.Success -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        onSuccess.invoke(it.data)
                    }
                }
            }
        }
    }
}