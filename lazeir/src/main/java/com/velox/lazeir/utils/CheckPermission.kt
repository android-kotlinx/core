package com.velox.lazeir.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat


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
