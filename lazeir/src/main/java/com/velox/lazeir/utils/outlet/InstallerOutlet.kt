package com.velox.lazeir.utils.outlet

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.velox.lazeir.utils.installer._unInstallApk

fun installApk(context: Context, uri: Uri, onError: (String) -> Unit={}) {
    return installApk(context, uri, onError)
}

fun installApk(context: Context, fileUri: String, packageName: String, onError: (String) -> Unit={}) {
    return installApk(context, fileUri, packageName,onError)
}

@RequiresApi(Build.VERSION_CODES.O)
fun unInstallApk(context: Context, packageName: String, onError: (String) -> Unit={}) {
    _unInstallApk(context, packageName, onError)
}

fun isAppInstalled(context: Context, packageName: String): Boolean {
    return isAppInstalled(context, packageName)
}