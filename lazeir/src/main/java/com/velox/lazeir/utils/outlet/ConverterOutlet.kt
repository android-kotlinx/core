package com.velox.lazeir.utils.outlet

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.velox.lazeir.utils.conveter.toBase64
import com.velox.lazeir.utils.conveter.toBitMapFromBase64
import com.velox.lazeir.utils.conveter.toDecodedBase64
import com.velox.lazeir.utils.conveter.toEncodedBase64


fun Uri.toByteArray(context: Context): ByteArray? {
    return  toByteArray(context)
}

@RequiresApi(Build.VERSION_CODES.O)
fun ByteArray?.toBase64(): String?  {
    return toBase64(this)
}


fun String?.toEncodedBase64(): String? {
    return toEncodedBase64(this)
}


fun String?.toDecodedBase64(): String? {
    return toDecodedBase64(this)
}

fun String?.toBitMapFromBase64(): Bitmap? = toBitMapFromBase64(this)




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




