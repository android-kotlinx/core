package com.androidx.core.outlet


import android.util.Log
import androidx.annotation.Keep
import com.androidx.core.domain.HandlerInterface
import com.androidx.core.utils.network_handler.Handler
import com.androidx.core.outlet.pub.KtorResource
import com.androidx.core.outlet.pub.RetrofitResource
import com.androidx.core.utils.network_handler.handleFlow
import com.androidx.core.utils.network_handler.handleFlowKtor
import com.androidx.core.utils.network_handler.handleNetworkResponse
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException

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
    return handleFlowKtor(this, onLoading, onFailure, onSuccess)
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
    return handleFlow(this, onLoading, onFailure, onSuccess)
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




