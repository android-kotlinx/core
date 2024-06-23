package com.androidx.core.outlet

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.annotation.Keep
import androidx.annotation.RequiresApi
import com.androidx.core.domain.ConverterInterface
import com.androidx.core.utils.conveter.Converter


private val converter:ConverterInterface = Converter()


@Keep
fun Uri.toByteArray(context: Context): ByteArray? {
    return  converter.toByteArray(this,context)
}

@RequiresApi(Build.VERSION_CODES.O)
@Keep
fun ByteArray?.toBase64(): String?  {
    return converter.toBase64(this)
}


@Keep
fun String?.toEncodedBase64(): String? {
    return converter.toEncodedBase64(this)
}


@Keep
fun String?.toDecodedBase64(): String? {
    return converter.toDecodedBase64(this)
}

@Keep
fun String?.toBitMapFromBase64(): Bitmap? = converter.toBitMapFromBase64(this)





@Keep
fun Bitmap?.cropCenter(): Bitmap? = converter.cropCenter(this)
@Keep
fun Bitmap?.toByteArray(): ByteArray? = converter.toByteArray(this)


@Keep
fun <T : Number> T.toWholeNumber(): T = converter.toWholeNumber(this)
@Keep
fun <T : Number> T.toOneDigits(): T = converter.toOneDigits(this)
@Keep
fun <T : Number> T.toTwoDigits(): T = converter.toTwoDigits(this)
@Keep
fun <T : Number> T.toThreeDigits(): T = converter.toThreeDigits(this)
@Keep
fun <T : Number> T.toFourDigits(): T = converter.toFourDigits(this)
@Keep
fun <T : Number> T.toFiveDigits(): T = converter.toFiveDigits(this)
@Keep
fun <T : Number> T.toSixDigits(): T = converter.toSixDigits(this)
@Keep
fun <T : Number> T.toSevenDigits(): T = converter.toSevenDigits(this)

/**
 * Converts any number to "HH:mm:ss:SS" format string
 * */
@Keep
fun Number.toTime(): String = converter.toTime(this)

@Keep
fun convertMillisToDate(millis: Long?, pattern: String): String = converter.inConvertMillisToDate(millis, pattern)


@Keep
fun convertToWords(amount: Double): String = converter.inConvertToWords(amount)