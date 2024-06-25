package com.androidx.core.utils.network_handler

import android.util.Log
import com.androidx.core.outlet.pub.KtorResource
import com.androidx.core.outlet.pub.RetrofitResource
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
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import org.json.JSONObject
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException


 inline fun < reified T> handleNetworkResponse(crossinline call: suspend () -> HttpResponse): Flow<KtorResource<T>> {
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


internal inline fun <T> handleFlowKtor(
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

internal inline fun <T> handleFlow(
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