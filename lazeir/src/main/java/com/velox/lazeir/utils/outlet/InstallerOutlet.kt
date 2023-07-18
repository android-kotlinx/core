package com.velox.lazeir.utils.outlet

import android.content.Context
import android.net.Uri
import com.velox.lazeir.utils.installer

fun installApk(context: Context, uri: Uri){
    return installer.installApk(context, uri)
}

fun unInstallApk(context: Context, packageName: String){
    installer.unInstallApk(context, packageName)
}

fun isAppInstalled(context: Context, packageName: String): Boolean{
    return installer.isAppInstalled(context, packageName)
}