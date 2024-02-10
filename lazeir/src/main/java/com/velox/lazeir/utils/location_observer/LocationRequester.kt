package com.velox.lazeir.utils.location_observer

import android.app.Activity
import android.content.IntentSender
import android.widget.Toast
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority

internal fun requestLocationEnable(activity: Activity?, result: (Boolean) -> Unit = {}) {

    activity?.let { act ->
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, 1000
        ).setMinUpdateIntervalMillis(1000 / 2).setWaitForAccurateLocation(false)
            .setMaxUpdateDelayMillis(1000).build()


        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)

        val client = LocationServices.getSettingsClient(act)
        val task = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
                        result(it.locationSettingsStates?.isLocationPresent == true)
        }.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(act, 999)
                } catch (sendEx: IntentSender.SendIntentException) {
                    sendEx.printStackTrace()
                }
            } else {
                // Handle other failures
                Toast.makeText(activity, "Failed to enable location", Toast.LENGTH_SHORT).show()
                result(false)

            }
        }
    }
}

