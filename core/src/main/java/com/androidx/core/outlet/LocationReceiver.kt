package com.androidx.core.outlet

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.LocationListener
import android.os.Build
import androidx.annotation.RequiresApi
import com.androidx.core.outlet.pub.CurrentLocationData
import com.androidx.core.utils.location_observer.LocationRepository


class LocationReceiver {

    private val repo = LocationRepository()

    fun isGpsHardwareEnabled(context: Context) =
        repo.inIsGpsHardwareEnabled(context)
    val isGpsState = repo.isGpsEnabled

    fun requestLocationEnabler(activity: Activity?, result: (Boolean) -> Unit) =
        repo.requestLocationEnabler(activity, result)

    fun openLocationSetting(context: Context) = repo.openLocationSetting(context)

    fun registerGpsStateReceiver(context: Context) =
        repo.registerGpsStateReceiver(context)

    fun unregisterGpsStateReceiver(context: Context) =
        repo.unregisterGpsStateReceiver(context)

    fun getLocation(
        context: Context, onResult: (CurrentLocationData?) -> Unit,
        onFailure: (String) -> Unit,
        onGpsEnabled: (String) -> Unit = {},
        onGpsDisabled: (String) -> Unit = {},
        getLocationOnes:Boolean  = true,
    ): LocationListener? {
        return repo.getLocation(
            context,
            onResult,
            onFailure,
            onGpsEnabled,
            onGpsDisabled,getLocationOnes
        )
    }

    fun removeGpsListener(listener: LocationListener){
        repo.removeGpsListener(listener)
    }


    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getCurrentLocation(context: Context, priority: Int): CurrentLocationData? =
        repo.getCurrentLocation(context, priority)

}


