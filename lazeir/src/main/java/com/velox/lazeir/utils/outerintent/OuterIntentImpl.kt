package com.velox.lazeir.utils.outerintent

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast

class OuterIntentImpl:OuterIntentInterface {
    override fun <T> startActivity(
        context: Context,
        packageName: String,
        activityName: String,
        dataToIntent: List<Pair<String, T>>?
    ) {
        try {
            val action = "$packageName.$activityName"
            val intent = Intent(action)
            intent.setPackage(packageName)
            if (dataToIntent != null){
                for (data in dataToIntent) {
                    val (key,value) = data
                    when(value){
                        is String -> intent.putExtra(key,value as String)
                        is Int -> intent.putExtra(key,value as Int)
                    }
                }
            }
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Unable to start $activityName", Toast.LENGTH_SHORT).show()
        }
    }
}