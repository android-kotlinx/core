package com.velox.lazeir.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.ContextCompat

/**
 * Then we check for different types of network transports like WIFI, CELLULAR, and ETHERNET.
 *
 * If any of these transports are available, we assume that the internet is accessible.
 * **/
fun Context.isInternetAvailable(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        // For other device-based internet connections such as Ethernet
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}
fun checkForPermission(permission: String, context: Context): Boolean =
    context.packageManager.checkPermission(
        permission, context.packageName
    ) == PackageManager.PERMISSION_GRANTED

/**
 * Checks whether Location Permission is granted or not
 * **/
fun Context.hasLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
        this, Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

/*
@Composable
fun checkForPermission(permission: String): Boolean {
    val context = LocalContext.current
    return (ContextCompat.checkSelfPermission(
        context, permission
    ) == PackageManager.PERMISSION_GRANTED)
}

@Composable
fun ComposablePermissionRequest(
    permission: String,
    onGranted: () -> Unit = {},
    onNotGranted: () -> Unit = {},
    onAlreadyGranted: () -> Unit = {},
) {
    val context = LocalContext.current
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            onGranted()
        } else {
            // permission not granted, handle the error
            onNotGranted()
        }
    }

//    val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE // replace with the permission you want to request

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context, permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // permission already granted, perform the action
            onAlreadyGranted()
        } else {
            requestPermissionLauncher.launch(permission)
        }
    }
}*/
