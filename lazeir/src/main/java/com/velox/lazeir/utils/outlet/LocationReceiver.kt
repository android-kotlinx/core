package com.velox.lazeir.utils.outlet

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.velox.lazeir.utils.location_observer.CurrentLocationData
import com.velox.lazeir.utils.location_observer.LocationRepository

class LocationReceiver {
    private val locationRepository = LocationRepository()

    val isGpsEnabled = locationRepository.isGpsEnabled
   fun requestLocationEnabler(activity: Activity?, result: (Boolean) -> Unit) = locationRepository.requestLocationEnabler(activity, result)

    fun  openLocationSetting(context: Context) = locationRepository.openLocationSetting(context)

    fun registerGpsStateReceiver(context: Context) = locationRepository.registerGpsStateReceiver(context)

    fun unregisterGpsStateReceiver(context: Context) = locationRepository.unregisterGpsStateReceiver(context)

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getCurrentLocation(context: Context, priority: Int): CurrentLocationData? = locationRepository.getCurrentLocation(context, priority)

}