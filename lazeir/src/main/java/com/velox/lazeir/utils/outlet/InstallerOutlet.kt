package com.velox.lazeir.utils.outlet

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.velox.lazeir.utils.installer.inInstallApk
import com.velox.lazeir.utils.installer.inIsAppInstalled
import com.velox.lazeir.utils.installer.inUninstallApk

fun installApk(context: Context, uri: Uri, onError: (String) -> Unit={}) {
    return inInstallApk(context, uri, onError)
}

fun installApk(context: Context, fileUri: String, packageName: String, onError: (String) -> Unit={}) {
    return inInstallApk(context, fileUri, packageName,onError)
}

@RequiresApi(Build.VERSION_CODES.O)
fun unInstallApk(context: Context, packageName: String, onError: (String) -> Unit={}) {
    inUninstallApk(context, packageName, onError)
}

fun isAppInstalled(context: Context, packageName: String): Boolean {
    return inIsAppInstalled(context, packageName)
}