package com.velox.lazeir.utils.location_observer

import com.velox.lazeir.hasLocationPermission
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import androidx.annotation.Keep
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.tasks.await


internal class LocationRepository  {

    val isGpsEnabled = mutableStateOf(false)
    fun requestLocationEnabler(activity: Activity?, result: (Boolean) -> Unit) {
        requestLocationEnable(activity, result)
    }

    private val gpsStateReceiver = GpsConnectivityObserver(isGpsEnabled)
    private val isLocationReceiverRegistered = mutableStateOf(false)



    private fun checkIsGpsEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    fun openLocationSetting(context: Context) {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun registerGpsStateReceiver(context: Context) {
        val intentFilter = IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        context.registerReceiver(gpsStateReceiver, intentFilter)
        isLocationReceiverRegistered.value = true
    }

    fun unregisterGpsStateReceiver(context: Context) {
        if (isLocationReceiverRegistered.value) {
            context.unregisterReceiver(gpsStateReceiver)
            isLocationReceiverRegistered.value = false
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(context: Context, priority: Int): CurrentLocationData? {
        if (context.hasLocationPermission()) {
            if (checkIsGpsEnabled(context)) {
                val locationClient = LocationServices.getFusedLocationProviderClient(context)
                val result = locationClient.getCurrentLocation(priority, CancellationTokenSource().token)
                        .await()
                return result?.let { fetchedLocation ->
                    return@let CurrentLocationData(
                        lat = fetchedLocation.latitude.toString(),
                        long = fetchedLocation.longitude.toString(),
                        alt = fetchedLocation.altitude.toString(),
                        bearing = fetchedLocation.bearing.toString(),
                        accuracy = fetchedLocation.accuracy,
                        verticalAccuracy = fetchedLocation.verticalAccuracyMeters
                    )
                }
            } else {
                return null
            }
        } else {
            return null
        }
    }
}

@Keep
data class CurrentLocationData(
    val lat: String,
    val long: String,
    val alt: String? = null,
    val bearing: String? = null,
    val accuracy: Float? = null,
    val verticalAccuracy: Float? = null,
)