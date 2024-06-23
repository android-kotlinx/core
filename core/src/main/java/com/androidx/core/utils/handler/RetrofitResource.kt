package com.androidx.core.utils.handler

import kotlinx.serialization.json.JsonObject
import org.json.JSONObject


/**
 * Author: [cr velox]
 * */



@Deprecated("use KtorResource instead",)
typealias NetworkResourceKtor<T> = KtorResource<T>

@Deprecated("use RetrofitResource instead",)
typealias NetworkResource<T> = RetrofitResource<T>

sealed class RetrofitResource<T>(
    val data: T? = null,
    val message: String? = null,
    val errorObject: JSONObject? = null,
    val code: Int? = null
) {
    class Success<T>(data: T?) : RetrofitResource<T>(data)

    class Error<T>(message: String, errorObject: JSONObject? = JSONObject(), code: Int? = -100) :
        RetrofitResource<T>(null, message, errorObject, code)

    class Loading<T>(val isLoading: Boolean) : RetrofitResource<T>(null)

}
sealed class NetworkResultResource<T>(
    val data: T? = null,
    val message: String? = null,
    val errorObject: Any? = null,
    val code: Int? = null
) {
    class Success<T>(data: T?) : NetworkResultResource<T>(data)

    class Error<T>(message: String, errorObject: Any? = Any(), code: Int? = -100) :
        NetworkResultResource<T>(null, message, errorObject, code)

    class Loading<T>(val isLoading: Boolean) : NetworkResultResource<T>(null)

}

sealed class KtorResource<T>(
    val data: T? = null,
    val message: String? = null,
    val errorObject: JsonObject? = null,
    val code: Int? = null
) {
    class Success<T>(data: T?) : KtorResource<T>(data)

    class Error<T>(message: String, errorObject: JsonObject? = null, code: Int? = -100) :
        KtorResource<T>(null, message, errorObject, code)

    class Loading<T>(val isLoading: Boolean) : KtorResource<T>(null)

}