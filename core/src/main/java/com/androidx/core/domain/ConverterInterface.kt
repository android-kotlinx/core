package com.androidx.core.domain

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope

internal interface ConverterInterface {

    @SuppressLint("Recycle")
    fun toByteArray(uri: Uri, context: Context): ByteArray?
    @RequiresApi(Build.VERSION_CODES.O)
    fun toBase64(byteArray: ByteArray?): String?
    fun toEncodedBase64(string: String?): String?
    fun toDecodedBase64(string: String?): String?
    fun toBitMapFromBase64(string: String?): Bitmap?
    fun cropCenter(bitmap: Bitmap?): Bitmap?
    fun toByteArray(bitmap: Bitmap?): ByteArray?
    fun <T : Number> toWholeNumber(number: T): T
    fun <T : Number> toOneDigits(number: T): T
    fun <T : Number> toTwoDigits(number: T): T
    fun <T : Number> toThreeDigits(number: T): T
    fun <T : Number> toFourDigits(number: T): T
    fun <T : Number> toFiveDigits(number: T): T
    fun <T : Number> toSixDigits(number: T): T
    fun <T : Number> toSevenDigits(number: T): T
    fun toTime(number: Number): String
    fun getBitmapFromUri(contentResolver: ContentResolver, uri: Uri): Bitmap?
    fun saveBitmapImageToFile(bitmap: Bitmap, context: Context, name: String): Uri?
    fun uriToBitmap(contentResolver: ContentResolver, imageUri: Uri): Bitmap?
    fun ByteArray.toBitmap(): Bitmap
    fun String.log(tag: String = "Log"): Int
    fun Context.toast(m: String)
    val ioScope: CoroutineScope
    val mainScope: CoroutineScope
    fun CharSequence?.isNotNullOrEmpty(): Boolean
    fun inConvertToWords(amount: Double): String
    @SuppressLint("SimpleDateFormat")
    fun inConvertMillisToDate(millis: Long?, pattern: String): String
}