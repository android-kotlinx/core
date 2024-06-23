package com.androidx.core.outlet.pub

import androidx.annotation.Keep
import org.json.JSONObject

@Keep
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