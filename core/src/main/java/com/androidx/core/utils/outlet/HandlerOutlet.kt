package com.androidx.core.utils.outlet

import android.annotation.SuppressLint
import com.androidx.core.utils.handler.RetrofitResource
import com.androidx.core.utils.handler.KtorResource
import com.androidx.core.utils.handler.handleFlow
import com.androidx.core.utils.handler.handleFlowKtor
import com.androidx.core.utils.handler.handleNetworkCall
import com.androidx.core.utils.handler.handleNetworkResponse
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response


fun <T, O> handleNetworkResponse(
    call: suspend () -> Response<T>, mapFun: (it: T) -> O
): Flow<RetrofitResource<O>> {
    return handleNetworkResponse(call, mapFun)
}

/**
 * [handleNetworkResponse] handle the API response,
 * convert the dto response to domain response
 * extracting the error according to the error code
 *
 * Way to use
 *
 * In Implementation
 *
 * override suspend fun requestData(): Flow<NetworkResource<T>>
 *
 *      {
 *      return apiService.requestData().handleNetworkResponse()
 *      }
 *
 * */
fun <T> Response<T>.handleNetworkResponse(): Flow<RetrofitResource<T>> {
    return handleNetworkResponse(this)
}


/**
 * [handleFlow] takes the response from use case function as Resource<> with in Main Coroutine Scope
 * return the extracted response with in onLoading(),onFailure(),onSuccess()
 * Call within IO Scope
 * **/
fun <T> Flow<RetrofitResource<T>>.handleFlow(
    onLoading: suspend (it: Boolean) -> Unit,
    onFailure: suspend (it: String?, errorObject: JSONObject?, code: Int?) -> Unit,
    onSuccess: suspend (it: T?) -> Unit
) {
    return handleFlow(this, onLoading, onFailure, onSuccess)
}

fun <T> Flow<RetrofitResource<T>>.handleFlowWithScope(
    onLoading: suspend (it: Boolean) -> Unit,
    onFailure: suspend (it: String?, errorObject: JSONObject?, code: Int?) -> Unit,
    onSuccess: suspend (it: T?) -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        return@launch handleFlow(this@handleFlowWithScope, onLoading, onFailure, onSuccess)
    }
}


@SuppressLint("LogNotTimber")
fun <T> Call<T>.handleNetworkCall(): Flow<RetrofitResource<T>> {
    return handleNetworkCall(this)
}


inline fun <reified T> handleNetworkResponse(crossinline call: suspend () -> HttpResponse): Flow<KtorResource<T>> {
    return handleNetworkResponse(call)
}


fun <T> Flow<KtorResource<T>>.handleKtorFlow(
    onLoading: suspend (it: Boolean) -> Unit,
    onFailure: suspend (it: String?, errorObject: JsonObject?, code: Int?) -> Unit,
    onSuccess: suspend (it: T?) -> Unit
) {
    return handleFlowKtor(this, onLoading, onFailure, onSuccess)
}