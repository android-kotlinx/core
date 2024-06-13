package com.androidx.core.utils.network_handler

import kotlinx.serialization.json.JsonObject

@Deprecated("use KtorResource instead",)
typealias NetworkResourceKtor<T> = KtorResource<T>

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