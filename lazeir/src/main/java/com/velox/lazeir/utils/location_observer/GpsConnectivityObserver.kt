package com.velox.lazeir.utils.location_observer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import androidx.compose.runtime.MutableState

const val UPDATE_FREQUENCY = 500//250
const val DELAY_MILLIS = 1000L

/**
 * This class tells when the GPS is turned on or off
* */
internal class GpsConnectivityObserver(private val state: MutableState<Boolean>) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == LocationManager.PROVIDERS_CHANGED_ACTION) {
            val locationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            state.value = gpsEnabled

        }
    }
}