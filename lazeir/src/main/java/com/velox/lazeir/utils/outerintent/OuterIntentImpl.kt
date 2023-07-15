package com.velox.lazeir.utils.outerintent

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import java.io.Serializable

class OuterIntentImpl : OuterIntentInterface {
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
            if (dataToIntent != null) {
                for (data in dataToIntent) {
                    val (key, value) = data
                    when (value) {
                        is String -> intent.putExtra(key, value as String)
                        is Int -> intent.putExtra(key, value as Int)
                        is Boolean -> intent.putExtra(key, value as Boolean)
                        is Bundle -> intent.putExtra(key, value as Bundle)
                        is CharSequence -> intent.putExtra(key, value as CharSequence)
                        is BooleanArray -> intent.putExtra(key, value as BooleanArray)
                        is Byte -> intent.putExtra(key, value as Byte)
                        is ByteArray -> intent.putExtra(key, value as ByteArray)
                        is Char -> intent.putExtra(key, value as Char)
                        is CharArray -> intent.putExtra(key, value as CharArray)
                        is Double -> intent.putExtra(key, value as Double)
                        is DoubleArray -> intent.putExtra(key, value as DoubleArray)
                        is Float -> intent.putExtra(key, value as Float)
                        is FloatArray -> intent.putExtra(key, value as FloatArray)
                        is IntArray -> intent.putExtra(key, value as IntArray)
                        is Long -> intent.putExtra(key, value as Long)
                        is LongArray -> intent.putExtra(key, value as LongArray)
                        is Short -> intent.putExtra(key, value as Short)
                        is ShortArray -> intent.putExtra(key, value as ShortArray)
                        is Parcelable -> intent.putExtra(key, value as Parcelable)
                        is Serializable -> intent.putExtra(key, value as Serializable)
                    }
                }
            }
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException)
    {
        Toast.makeText(context, "Unable to start $activityName", Toast.LENGTH_SHORT).show()
    }
}
}