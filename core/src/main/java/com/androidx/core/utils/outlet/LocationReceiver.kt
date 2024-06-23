package com.androidx.core.utils.outlet

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.LocationListener
import android.os.Build
import androidx.annotation.RequiresApi
import com.androidx.core.utils.location_observer.CurrentLocationData
import com.androidx.core.utils.location_observer.LocationRepository

class LocationReceiver {
    private val locationRepository = LocationRepository()
    fun isGpsHardwareEnabled(context: Context) =
        locationRepository.inIsGpsHardwareEnabled(context)

    val isGpsState = locationRepository.isGpsEnabled
    fun requestLocationEnabler(activity: Activity?, result: (Boolean) -> Unit) =
        locationRepository.requestLocationEnabler(activity, result)

    fun openLocationSetting(context: Context) = locationRepository.openLocationSetting(context)

    fun registerGpsStateReceiver(context: Context) =
        locationRepository.registerGpsStateReceiver(context)

    fun unregisterGpsStateReceiver(context: Context) =
        locationRepository.unregisterGpsStateReceiver(context)

    fun getLocation(
        context: Context, onResult: (CurrentLocationData?) -> Unit,
        onFailure: (String) -> Unit,
        onGpsEnabled: (String) -> Unit = {},
        onGpsDisabled: (String) -> Unit = {},
        getLocationOnes:Boolean  = true,
    ): LocationListener? {
        return locationRepository.getLocation(
            context,
            onResult,
            onFailure,
            onGpsEnabled,
            onGpsDisabled,getLocationOnes
        )
    }

    fun removeGpsListener(listener: LocationListener){
        locationRepository.removeGpsListener(listener)
    }




    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getCurrentLocation(context: Context, priority: Int): CurrentLocationData? =
        locationRepository.getCurrentLocation(context, priority)


}


