package com.velox.lazeir.utils.outlet

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.velox.lazeir.utils.conveter.cropCenter
import com.velox.lazeir.utils.conveter.inConvertMillisToDate
import com.velox.lazeir.utils.conveter.inConvertToWords
import com.velox.lazeir.utils.conveter.toBase64
import com.velox.lazeir.utils.conveter.toBitMapFromBase64
import com.velox.lazeir.utils.conveter.toByteArray
import com.velox.lazeir.utils.conveter.toDecodedBase64
import com.velox.lazeir.utils.conveter.toEncodedBase64
import com.velox.lazeir.utils.conveter.toFiveDigits
import com.velox.lazeir.utils.conveter.toFourDigits
import com.velox.lazeir.utils.conveter.toOneDigits
import com.velox.lazeir.utils.conveter.toSevenDigits
import com.velox.lazeir.utils.conveter.toSixDigits
import com.velox.lazeir.utils.conveter.toThreeDigits
import com.velox.lazeir.utils.conveter.toTime
import com.velox.lazeir.utils.conveter.toTwoDigits
import com.velox.lazeir.utils.conveter.toWholeNumber


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





fun Bitmap?.cropCenter(): Bitmap? = cropCenter(this)
fun Bitmap?.toByteArray(): ByteArray? = toByteArray(this)


fun <T : Number> T.toWholeNumber(): T = toWholeNumber(this)
fun <T : Number> T.toOneDigits(): T = toOneDigits(this)
fun <T : Number> T.toTwoDigits(): T = toTwoDigits(this)
fun <T : Number> T.toThreeDigits(): T = toThreeDigits(this)
fun <T : Number> T.toFourDigits(): T = toFourDigits(this)
fun <T : Number> T.toFiveDigits(): T = toFiveDigits(this)
fun <T : Number> T.toSixDigits(): T = toSixDigits(this)
fun <T : Number> T.toSevenDigits(): T = toSevenDigits(this)

/**
 * Converts any number to "HH:mm:ss:SS" format string
 * */
fun Number.toTime(): String = toTime(this)

fun convertMillisToDate(millis: Long?, pattern: String): String = inConvertMillisToDate(millis, pattern)


 fun convertToWords(amount: Double): String = inConvertToWords(amount)

