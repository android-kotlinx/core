package com.androidx.core.outlet


import androidx.annotation.Keep
import com.androidx.core.domain.HandlerInterface
import com.androidx.core.utils.network_handler.Handler
import com.androidx.core.utils.network_handler.KtorResource
import com.androidx.core.utils.network_handler.RetrofitResource
import com.androidx.core.utils.network_handler.handleFlow
import com.androidx.core.utils.network_handler.handleFlowKtor
import com.androidx.core.utils.network_handler.handleNetworkResponse
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

val handler : HandlerInterface = Handler()
/*@Deprecated("use KtorResource instead",)
typealias NetworkResourceKtor<T> = KtorResource<T>*/

@Keep
fun <T, O> handleNetworkResponse(
    call: suspend () -> Response<T>, mapFun: (it: T) -> O
): Flow<RetrofitResource<O>> {
    return handler.handleNetworkResponse(call, mapFun)
}

@Keep
fun <T> Flow<KtorResource<T>>.handleKtorFlow(
    onLoading: suspend (it: Boolean) -> Unit,
    onFailure: suspend (it: String?, errorObject: JsonObject?, code: Int?) -> Unit,
    onSuccess: suspend (it: T?) -> Unit
) {
    return handler.handleFlowKtor(this, onLoading, onFailure, onSuccess)
}


/**
 * [handleFlow] takes the response from use case function as Resource<> with in Main Coroutine Scope
 * return the extracted response with in onLoading(),onFailure(),onSuccess()
 * Call within IO Scope
 * **/
@Keep
fun <T> Flow<RetrofitResource<T>>.handleFlow(
    onLoading: suspend (it: Boolean) -> Unit,
    onFailure: suspend (it: String?, errorObject: JSONObject?, code: Int?) -> Unit,
    onSuccess: suspend (it: T?) -> Unit
) {
    return handler.handleFlow(this, onLoading, onFailure, onSuccess)
}
/**
 * [handleNetworkResponse] handle the API response,
 * convert the dto response to domain response
 * extracting the error according to the error code
 *
 * */
@Keep
fun <T> Response<T>.handleNetworkResponse(): Flow<RetrofitResource<T>> {
    return handler.handleNetworkResponse(this)
}



@Keep
fun <T> Call<T>.handleNetworkCall(): Flow<RetrofitResource<T>> {
    return handler.handleNetworkCall(this)
}


/**
 * A function that handles network responses and transforms them into a Flow of [KtorResource] objects.
 * This function takes a suspending function [call] as a parameter, which is expected to return an [HttpResponse].
 * The function then uses the [handleNetworkResponse] function to process the response and emit [KtorResource] objects
 * to the returned Flow.
 *
 * @param T The type of data expected in the network response.
 * @param call A suspending function that makes the network request and returns an [HttpResponse].
 * @return A Flow of [KtorResource] objects representing the processed network response.
 */
@Keep
inline fun <reified T> handleNetworkResponse(crossinline call: suspend () -> HttpResponse): Flow<KtorResource<T>> {
    return handler.handleNetworkResponse(call)
}


