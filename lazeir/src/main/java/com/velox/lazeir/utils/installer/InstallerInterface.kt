package com.velox.lazeir.utils.installer

import android.content.Context
import android.net.Uri

interface InstallerInterface {

    fun installApk(context: Context, uri: Uri)

    fun unInstallApk(context: Context, packageName: String)

    fun isAppInstalled(context: Context, packageName: String): Boolean
}