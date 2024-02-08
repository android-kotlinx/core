package com.velox.lazeir.utils.handler

import kotlinx.serialization.json.JsonObject
import org.json.JSONObject


/**
 * Author: [cr velox]
 * */

typealias NetworkResourceRetrofit<T> = NetworkResource<T>

sealed class NetworkResource<T>(
    val data: T? = null,
    val message: String? = null,
    val errorObject: JSONObject? = null,
    val code: Int? = null
) {
    class Success<T>(data: T?) : NetworkResource<T>(data)

    class Error<T>(message: String, errorObject: JSONObject? = JSONObject(), code: Int? = -100) :
        NetworkResource<T>(null, message, errorObject, code)

    class Loading<T>(val isLoading: Boolean) : NetworkResource<T>(null)

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

sealed class NetworkResourceKtor<T>(
    val data: T? = null,
    val message: String? = null,
    val errorObject: JsonObject? = null,
    val code: Int? = null
) {
    class Success<T>(data: T?) : NetworkResourceKtor<T>(data)

    class Error<T>(message: String, errorObject: JsonObject? = null, code: Int? = -100) :
        NetworkResourceKtor<T>(null, message, errorObject, code)

    class Loading<T>(val isLoading: Boolean) : NetworkResourceKtor<T>(null)

}