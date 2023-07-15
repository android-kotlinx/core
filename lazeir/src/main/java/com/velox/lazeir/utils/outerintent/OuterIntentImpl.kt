package com.velox.lazeir.utils.outerintent

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast

class OuterIntentImpl:OuterIntentInterface {
    override fun startActivity(context: Context, packageName: String, activityName: String) {
        try {
            val action = "$packageName.$activityName"
            val launchIntent = Intent(action)
            launchIntent.setPackage(packageName)
            launchIntent.putExtra("data", "launchIntent")
            context.startActivity(launchIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Unable to start $activityName", Toast.LENGTH_SHORT).show()
        }
    }
}