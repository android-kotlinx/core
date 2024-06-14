package com.androidx.core.domain

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.LocationListener
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import com.androidx.core.utils.location_observer.CurrentLocationData

interface LocationInterface {

    val isGpsEnabled: MutableState<Boolean>
    fun requestLocationEnabler(activity: Activity?, result: (Boolean) -> Unit)
    fun inIsGpsHardwareEnabled(context: Context): Boolean
    fun openLocationSetting(context: Context)
    fun registerGpsStateReceiver(context: Context)
    fun unregisterGpsStateReceiver(context: Context)
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(context: Context, priority: Int): CurrentLocationData?
    @SuppressLint("MissingPermission")
    fun getLocation(
        context: Context,
        onResult: (CurrentLocationData?) -> Unit,
        onFailure: (String) -> Unit,
        onGpsEnabled: (String) -> Unit = {},
        onGpsDisabled: (String) -> Unit = {},
        getLocationOnes: Boolean
    ): LocationListener?

    fun removeGpsListener(listener: LocationListener)
}