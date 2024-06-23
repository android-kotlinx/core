package com.androidx.core.outlet

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.LocationListener
import android.os.Build
import androidx.annotation.Keep
import androidx.annotation.RequiresApi
import com.androidx.core.domain.LocationInterface
import com.androidx.core.utils.location_observer.CurrentLocationData
import com.androidx.core.utils.location_observer.LocationRepository

@Keep
class LocationReceiver {

    private val repo:LocationInterface = LocationRepository()
    @Keep
    fun isGpsHardwareEnabled(context: Context) =
        repo.inIsGpsHardwareEnabled(context)
    @Keep
    val isGpsState = repo.isGpsEnabled

    @Keep
    fun requestLocationEnabler(activity: Activity?, result: (Boolean) -> Unit) =
        repo.requestLocationEnabler(activity, result)

    @Keep
    fun openLocationSetting(context: Context) = repo.openLocationSetting(context)

    @Keep
    fun registerGpsStateReceiver(context: Context) =
        repo.registerGpsStateReceiver(context)

    @Keep
    fun unregisterGpsStateReceiver(context: Context) =
        repo.unregisterGpsStateReceiver(context)

    @Keep
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

    @Keep
    fun removeGpsListener(listener: LocationListener){
        repo.removeGpsListener(listener)
    }


    @Keep
    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getCurrentLocation(context: Context, priority: Int): CurrentLocationData? =
        repo.getCurrentLocation(context, priority)


}


