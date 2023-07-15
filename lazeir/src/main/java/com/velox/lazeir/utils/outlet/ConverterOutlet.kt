package com.velox.lazeir.utils.outlet

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.velox.lazeir.utils.converter


fun Uri.toByteArray(context: Context): ByteArray? {
    return  converter.toByteArray(this,context)
}

@RequiresApi(Build.VERSION_CODES.O)
fun ByteArray?.toBase64(): String?  {
    return converter.toBase64(this)
}


fun String?.toEncodedBase64(): String? {
    return converter.toEncodedBase64(this)
}


fun String?.toDecodedBase64(): String? {
    return converter.toDecodedBase64(this)
}

fun String?.toBitMapFromBase64(): Bitmap? = converter.toBitMapFromBase64(this)




fun Bitmap?.cropCenter(): Bitmap? = converter.cropCenter(this)
fun Bitmap?.toByteArray(): ByteArray? = converter.toByteArray(this)


fun <T : Number> T.toWholeNumber(): T = converter.toWholeNumber(this)
fun <T : Number> T.toOneDigits(): T = converter.toOneDigits(this)
fun <T : Number> T.toTwoDigits(): T = converter.toTwoDigits(this)
fun <T : Number> T.toThreeDigits(): T = converter.toThreeDigits(this)
fun <T : Number> T.toFourDigits(): T = converter.toFourDigits(this)
fun <T : Number> T.toFiveDigits(): T = converter.toFiveDigits(this)
fun <T : Number> T.toSixDigits(): T = converter.toSixDigits(this)
fun <T : Number> T.toSevenDigits(): T = converter.toSevenDigits(this)

/**
 * Converts any number to "HH:mm:ss:SS" format string
 * */
fun Number.toTime(): String = converter.toTime(this)




