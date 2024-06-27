package com.androidx.core.outlet

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.annotation.Keep
import androidx.annotation.RequiresApi
import com.androidx.core.utils.conveter.cropCenter
import com.androidx.core.utils.conveter.inConvertMillisToDate
import com.androidx.core.utils.conveter.inConvertToWords
import com.androidx.core.utils.conveter.toBase64
import com.androidx.core.utils.conveter.toBitMapFromBase64
import com.androidx.core.utils.conveter.toByteArray
import com.androidx.core.utils.conveter.toDecodedBase64
import com.androidx.core.utils.conveter.toEncodedBase64
import com.androidx.core.utils.conveter.toFiveDigits
import com.androidx.core.utils.conveter.toFourDigits
import com.androidx.core.utils.conveter.toOneDigits
import com.androidx.core.utils.conveter.toSevenDigits
import com.androidx.core.utils.conveter.toSixDigits
import com.androidx.core.utils.conveter.toThreeDigits
import com.androidx.core.utils.conveter.toTime
import com.androidx.core.utils.conveter.toTwoDigits
import com.androidx.core.utils.conveter.toWholeNumber


fun Uri.toByteArray(context: Context): ByteArray? {
    return  toByteArray(this,context)
}

@RequiresApi(Build.VERSION_CODES.O)
fun ByteArray?.toBase64(): String?  {
    return toBase64(this)
}



fun String?.toEncodedBase64(): String? = toEncodedBase64(this)



fun String?.toDecodedBase64(): String? = toDecodedBase64(this)


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