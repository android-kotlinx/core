package com.androidx.core

import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

internal fun Response<*>.getJSONObject(): JSONObject? {
    return try {
        errorBody()?.string()?.let { JSONObject(it) }
    } catch (exception: Exception) {
        null
    }
}

internal suspend inline fun <T> Call<T>.awaitHandler(): Response<T> = suspendCoroutine { continuation ->
    val callback = object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            continuation.resume(response)
        }

        override fun onFailure(call: Call<T>, t: Throwable) = continuation.resumeWithException(t)
    }
    enqueue(callback)
}