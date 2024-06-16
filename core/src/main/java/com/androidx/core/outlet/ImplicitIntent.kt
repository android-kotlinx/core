package com.androidx.core.outlet

import android.content.Context
import com.androidx.core.domain.ImplicitIntentInterface
import com.androidx.core.utils.implicit_intent.ImplicitIntent

private val intent :ImplicitIntentInterface = ImplicitIntent()


/**
 * [startOuterActivity] takes 3 parameters package name for the application,
 * activity name to be started in the following application and [Context]
 *
 * This is implemented on the parent application when the child application's activity
 * need to be launched.
 *
 * In child application manifest add the following code inside the
 *
 * < queries>
 * **< package android:name="com.example.package"/>**
 *
 * < /queries>
 *
 * < activity>
 *
 * < intent-filter>
 *
 * **< action android:name="com.example.package.ActivityName" / >**
 *
 * **< category android:name="android.intent.category.DEFAULT" / >**
 *
 * < /intent-filter>
 *
 * < /activity>
 *
 * remember to add  **implementation 'androidx.work:work-runtime-ktx:TAG'**

 * */
fun <T> Context.startImplicitActivity(
    packageName: String,
    activityName: String,
    dataToIntent: List<Pair<String, T?>?>?
) {
    intent.inStartImplicitActivity(this, packageName, activityName, dataToIntent)
}

