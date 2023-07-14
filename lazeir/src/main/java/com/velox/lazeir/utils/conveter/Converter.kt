package com.velox.lazeir.utils.conveter/*
package com.velox.lazeir.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


*/
/**
 * [toByteArray] Converting Uri to ByteArray
 * *//*

@SuppressLint("Recycle")
fun Uri.toByteArray(context: Context): ByteArray? {
    val iStream = context.contentResolver.openInputStream(this)
    val byteBuffer = ByteArrayOutputStream()
    val bufferSize = 1024
    val buffer = ByteArray(bufferSize)
    var len: Int
    return if (iStream != null) {
        while (iStream.read(buffer).also { len = it } != -1) {
            byteBuffer.write(buffer, 0, len)
        }
        byteBuffer.toByteArray()
    } else {
        null
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun ByteArray?.toBase64(): String? = try {
    String(java.util.Base64.getEncoder().encode(this))
} catch (e: Exception) {
    null
}


fun String?.toEncodedBase64(): String? = try {
    val bytes = this?.toByteArray(Charsets.UTF_8)
    Base64.encodeToString(bytes, Base64.DEFAULT)

} catch (e: Exception) {
    null
}


fun String?.toDecodedBase64(): String? =try {
        val bytes = this.let { Base64.decode(this, Base64.DEFAULT) }
        String(bytes, Charsets.UTF_8)
    } catch (e: Exception) {
        null
    }

fun String?.toBitMapFromBase64(): Bitmap? = try {
        val decodedBytes = Base64.decode(this, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    } catch (e: Exception) {
        null
    }



fun Bitmap?.cropCenter(): Bitmap? {
    return try {
        val centerX = (this?.width ?: 0) / 2
        val centerY = (this?.height ?: 0) / 2
        val width = 200
        val height = 200
        val left = centerX - (width / 2)
        val top = centerY - (height / 2)
        val right = centerX + (width / 2)
        val bottom = centerY + (height / 2)
        val croppedBitmap = Bitmap.createBitmap(this!!, left, top, width, height)
        croppedBitmap
    } catch (e: Exception) {
        null
    }

}

fun Bitmap?.toByteArray(): ByteArray? {
    try {
        ByteArrayOutputStream().use { stream ->
            this?.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            return stream.toByteArray()
        }
    } catch (e: Exception) {
        return null
    }
}


fun <T : Number> T.toWholeNumber(): T {
    val formattedNumber = DecimalFormat("00000").format(this).toDouble()
    @Suppress("UNCHECKED_CAST")
    return when (this) {
        is Byte -> formattedNumber.toInt().toByte() as T
        is Short -> formattedNumber.toInt().toShort() as T
        is Int -> formattedNumber.toInt() as T
        is Long -> formattedNumber.toLong() as T
        is Float -> formattedNumber.toFloat() as T
        is Double -> formattedNumber as T
        else -> throw IllegalArgumentException("Unsupported number type")
    }
}
fun <T : Number> T.toOneDigits(): T {
    val formattedNumber = DecimalFormat("00000.0").format(this).toDouble()
    @Suppress("UNCHECKED_CAST")
    return when (this) {
        is Byte -> formattedNumber.toInt().toByte() as T
        is Short -> formattedNumber.toInt().toShort() as T
        is Int -> formattedNumber.toInt() as T
        is Long -> formattedNumber.toLong() as T
        is Float -> formattedNumber.toFloat() as T
        is Double -> formattedNumber as T
        else -> throw IllegalArgumentException("Unsupported number type")
    }
}
fun <T : Number> T.toTwoDigits(): T {
    val formattedNumber = DecimalFormat("00000.00").format(this).toDouble()
    @Suppress("UNCHECKED_CAST")
    return when (this) {
        is Byte -> formattedNumber.toInt().toByte() as T
        is Short -> formattedNumber.toInt().toShort() as T
        is Int -> formattedNumber.toInt() as T
        is Long -> formattedNumber.toLong() as T
        is Float -> formattedNumber.toFloat() as T
        is Double -> formattedNumber as T
        else -> throw IllegalArgumentException("Unsupported number type")
    }
}
fun <T : Number> T.toThreeDigits(): T {
    val formattedNumber = DecimalFormat("00000.000").format(this).toDouble()
    @Suppress("UNCHECKED_CAST")
    return when (this) {
        is Byte -> formattedNumber.toInt().toByte() as T
        is Short -> formattedNumber.toInt().toShort() as T
        is Int -> formattedNumber.toInt() as T
        is Long -> formattedNumber.toLong() as T
        is Float -> formattedNumber.toFloat() as T
        is Double -> formattedNumber as T
        else -> throw IllegalArgumentException("Unsupported number type")
    }
}
fun <T : Number> T.toFourDigits(): T {
    val formattedNumber = DecimalFormat("00000.0000").format(this).toDouble()
    @Suppress("UNCHECKED_CAST")
    return when (this) {
        is Byte -> formattedNumber.toInt().toByte() as T
        is Short -> formattedNumber.toInt().toShort() as T
        is Int -> formattedNumber.toInt() as T
        is Long -> formattedNumber.toLong() as T
        is Float -> formattedNumber.toFloat() as T
        is Double -> formattedNumber as T
        else -> throw IllegalArgumentException("Unsupported number type")
    }
}
fun <T : Number> T.toFiveDigits(): T {
    val formattedNumber = DecimalFormat("00000.00000").format(this).toDouble()
    @Suppress("UNCHECKED_CAST")
    return when (this) {
        is Byte -> formattedNumber.toInt().toByte() as T
        is Short -> formattedNumber.toInt().toShort() as T
        is Int -> formattedNumber.toInt() as T
        is Long -> formattedNumber.toLong() as T
        is Float -> formattedNumber.toFloat() as T
        is Double -> formattedNumber as T
        else -> throw IllegalArgumentException("Unsupported number type")
    }
}
fun <T : Number> T.toSixDigits(): T {
    val formattedNumber = DecimalFormat("00000.000000").format(this).toDouble()
    @Suppress("UNCHECKED_CAST")
    return when (this) {
        is Byte -> formattedNumber.toInt().toByte() as T
        is Short -> formattedNumber.toInt().toShort() as T
        is Int -> formattedNumber.toInt() as T
        is Long -> formattedNumber.toLong() as T
        is Float -> formattedNumber.toFloat() as T
        is Double -> formattedNumber as T
        else -> throw IllegalArgumentException("Unsupported number type")
    }
}
fun <T : Number> T.toSevenDigits(): T {
    val formattedNumber = DecimalFormat("00000.0000000").format(this).toDouble()
    @Suppress("UNCHECKED_CAST")
    return when (this) {
        is Byte -> formattedNumber.toInt().toByte() as T
        is Short -> formattedNumber.toInt().toShort() as T
        is Int -> formattedNumber.toInt() as T
        is Long -> formattedNumber.toLong() as T
        is Float -> formattedNumber.toFloat() as T
        is Double -> formattedNumber as T
        else -> throw IllegalArgumentException("Unsupported number type")
    }
}

*/
/**
 * Converts any number to "HH:mm:ss:SS" format string
 * *//*

fun Number.toTime(): String {
    val sdf = SimpleDateFormat("HH:mm:ss:SS", Locale.getDefault())
    val time = Calendar.getInstance().apply { timeInMillis = this@toTime.toLong() }
    return sdf.format(time.time)
}


*/
/**
 * Checks whether Location Permission is granted or not
 * **//*

fun Context.hasLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
        this, Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

*/
/**
 * Then we check for different types of network transports like WIFI, CELLULAR, and ETHERNET.
 *
 * If any of these transports are available, we assume that the internet is accessible.
 * **//*

fun Context.isInternetAvailable(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        // For other device-based internet connections such as Ethernet
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }


}




*/
