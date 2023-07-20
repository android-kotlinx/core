package com.velox.lazeir.utils.outlet

import android.content.Context
import android.net.Uri
import com.velox.lazeir.utils.installer

fun installApk(context: Context, uri: Uri, onError: (String) -> Unit={}) {
    return installer.installApk(context, uri, onError)
}

fun unInstallApk(context: Context, packageName: String, onError: (String) -> Unit={}) {
    installer.unInstallApk(context, packageName, onError)
}

fun isAppInstalled(context: Context, packageName: String): Boolean {
    return installer.isAppInstalled(context, packageName)
}