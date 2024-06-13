package com.androidx.core.utils.network_handler

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