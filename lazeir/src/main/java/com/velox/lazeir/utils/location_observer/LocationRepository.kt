package com.velox.lazeir.utils.location_observer

import com.velox.lazeir.hasLocationPermission
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Location
import android.location.LocationListener
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
    private lateinit var locationManager: LocationManager

    private val gpsStateReceiver = GpsConnectivityObserver(isGpsEnabled)
    private val isLocationReceiverRegistered = mutableStateOf(false)



    fun inIsGpsHardwareEnabled(context: Context): Boolean {
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
            if (inIsGpsHardwareEnabled(context)) {
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

    @SuppressLint("MissingPermission")
    internal fun getLocation(
        context: Context, onResult: (CurrentLocationData?) -> Unit,
        onFailure: (String) -> Unit,
        onGpsEnabled: (String) -> Unit = {},
        onGpsDisabled: (String) -> Unit = {},
        getLocationOnes: Boolean,
                                ) :LocationListener ? {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if ( inIsGpsHardwareEnabled(context)) {
            val locationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    // Handle the new location
                    val currentLocationData = CurrentLocationData(
                        lat = location.latitude.toString(),
                        long = location.longitude.toString(),
                        alt = location.altitude.toString(),
                        bearing = location.bearing.toString(),
                        accuracy = location.accuracy,
                        verticalAccuracy = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            location.verticalAccuracyMeters
                        } else {
                            null
                        }
                    )

                    // Do something with the currentLocationData
                    onResult(currentLocationData)

                    // Stop location updates after receiving the first location
                    if (getLocationOnes){
                        locationManager.removeUpdates(this)
                    }
                }

                override fun onProviderEnabled(provider: String) {
                    super.onProviderEnabled(provider)
                    onGpsEnabled(provider)
                }

                override fun onProviderDisabled(provider: String) {
                    super.onProviderDisabled(provider)
                    onGpsDisabled(provider)
                }
            }

            if (getLocationOnes){

                locationManager.requestSingleUpdate(
                    LocationManager.GPS_PROVIDER,
                    locationListener,
                    null
                )
            }else{
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    0f,
                    locationListener
                )
            }


            return locationListener
        } else {
            onResult(null)
            onFailure("Please Turn On GPS")
            return null
        }
    }
    internal fun removeGpsListener(listener: LocationListener){
        locationManager.removeUpdates(listener)
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