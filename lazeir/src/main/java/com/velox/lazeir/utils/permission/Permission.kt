package ai.heart.lazier.permission

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

const val PERMISSION_REQUEST_CODE = 123 // You can choose any value for the request code

data class PermissionResult(val permission: String, val isGranted: Boolean)

fun checkAndRequestPermissions(activity: Activity, permissions: List<String>): List<PermissionResult> {
    val permissionsResultList = ArrayList<PermissionResult>()

    // Check each permission
    for (permission in permissions) {
        val permissionStatus = ContextCompat.checkSelfPermission(activity, permission)
        val isGranted = permissionStatus == PackageManager.PERMISSION_GRANTED
        permissionsResultList.add(PermissionResult(permission, isGranted))
    }

    // Filter permissions that need to be requested
    val permissionsToRequest = permissionsResultList.filter { !it.isGranted }

    // If any permissions need to be requested
    if (permissionsToRequest.isNotEmpty()) {
        // Convert the list to an array
        val permissionsArray = permissionsToRequest.map { it.permission }.toTypedArray()

        // Request the permissions
        ActivityCompat.requestPermissions(activity, permissionsArray, PERMISSION_REQUEST_CODE)
    }

    // Return the results
    return permissionsResultList
}

