package com.velox.lazeir.utils.installer

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast

class InstallerImpl:InstallerInterface {
    @SuppressLint("QueryPermissionsNeeded")
    override fun installApk(context: Context, uri: Uri) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
                .also {
                    it.setDataAndType(uri, "application/vnd.android.package-archive")
                    it.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                    it.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true)
//                    it.putExtra(Intent.EXTRA_RETURN_RESULT, true)
                    it.putExtra(Intent.EXTRA_INSTALLER_PACKAGE_NAME, context.applicationInfo.packageName)
                }
//            intent.setDataAndType(uri, "application/vnd.android.package-archive")
//            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
//            intent.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
//            intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
//            intent.putExtra(Intent.EXTRA_INSTALLER_PACKAGE_NAME, context.applicationInfo.packageName)
//        startActivity(intent)
            val activities = context.packageManager.queryIntentActivities(intent, 0)
            if (activities.isNotEmpty()) {
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "Install failed, No handler application found !", Toast.LENGTH_SHORT).show()
            }
        }catch (e:Exception){
            Toast.makeText(context, "Install failed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun unInstallApk(context: Context, packageName: String) {
        val intent = Intent(Intent.ACTION_DELETE).apply {
            this.data = Uri.parse(
                "package:$packageName"
            )
            this.putExtra(Intent.EXTRA_RETURN_RESULT, true)
        }
//        intent.putExtra(Intent.EXTRA_RETURN_RESULT, true)
//        startActivityForResult(intent, REQUEST_UNINSTALL)
        context.startActivity(intent)
    }

    override fun isAppInstalled(context: Context, packageName: String): Boolean {
        val packageManager = context.packageManager
        return try {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}