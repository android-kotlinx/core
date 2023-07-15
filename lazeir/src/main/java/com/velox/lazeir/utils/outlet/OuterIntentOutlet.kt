package com.velox.lazeir.utils.outlet

import android.content.Context
import com.velox.lazeir.utils.outerIntent


/**
 * [startOuterActivity] takes 3 parameters package name for the application,
 * activity name to be started in the following application and [Context]
 *
 * This is implemented on the parent application when the child application's activity
 * need to be launched.
 *
 * In child application manifest add the following code inside the
 *
 * --> activity tag
 *
 * --> intent-filter tag
 *
 * action android:name="com.example.package.ActivityName"
 *
 * category android:name="android.intent.category.DEFAULT"
 * */
fun Context.startOuterActivity(packageName: String, activityName: String) {
    outerIntent.startActivity(this, packageName, activityName)
}