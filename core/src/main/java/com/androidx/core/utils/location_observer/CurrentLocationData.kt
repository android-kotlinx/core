package com.androidx.core.utils.location_observer

import androidx.annotation.Keep

@Keep
data class CurrentLocationData(
    val lat: String,
    val long: String,
    val alt: String? = null,
    val bearing: String? = null,
    val accuracy: Float? = null,
    val verticalAccuracy: Float? = null,
)