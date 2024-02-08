package com.velox.lazeir.utils.conveter

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@SuppressLint("Recycle")
internal fun toByteArray(uri: Uri, context: Context): ByteArray? {
    val iStream = context.contentResolver.openInputStream(uri)
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
internal fun toBase64(byteArray: ByteArray?): String? = try {
    String(java.util.Base64.getEncoder().encode(byteArray))
} catch (e: Exception) {
    null
}


internal fun toEncodedBase64(string: String?): String? = try {
    val bytes = string?.toByteArray(Charsets.UTF_8)
    Base64.encodeToString(bytes, Base64.DEFAULT)

} catch (e: Exception) {
    null
}


internal fun toDecodedBase64(string: String?): String? = try {
    val bytes = string.let { Base64.decode(it, Base64.DEFAULT) }
    String(bytes, Charsets.UTF_8)
} catch (e: Exception) {
    null
}

internal fun toBitMapFromBase64(string: String?): Bitmap? = try {
    val decodedBytes = Base64.decode(string, Base64.DEFAULT)
    BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
} catch (e: Exception) {
    null
}


internal fun cropCenter(bitmap: Bitmap?): Bitmap? {
    return try {
        val centerX = (bitmap?.width ?: 0) / 2
        val centerY = (bitmap?.height ?: 0) / 2
        val width = 200
        val height = 200
        val left = centerX - (width / 2)
        val top = centerY - (height / 2)
//            val right = centerX + (width / 2)
//            val bottom = centerY + (height / 2)
        val croppedBitmap = Bitmap.createBitmap(bitmap!!, left, top, width, height)
        croppedBitmap
    } catch (e: Exception) {
        null
    }
}

internal fun toByteArray(bitmap: Bitmap?): ByteArray? {
    try {
        ByteArrayOutputStream().use { stream ->
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            return stream.toByteArray()
        }
    } catch (e: Exception) {
        return null
    }
}


internal fun <T : Number> toWholeNumber(number: T): T {
    val formattedNumber = DecimalFormat("00000").format(number).toDouble()
    @Suppress("UNCHECKED_CAST")
    return when (number) {
        is Byte -> formattedNumber.toInt().toByte() as T
        is Short -> formattedNumber.toInt().toShort() as T
        is Int -> formattedNumber.toInt() as T
        is Long -> formattedNumber.toLong() as T
        is Float -> formattedNumber.toFloat() as T
        is Double -> formattedNumber as T
        else -> throw IllegalArgumentException("Unsupported number type")
    }
}

internal fun <T : Number> toOneDigits(number: T): T {
    val formattedNumber = DecimalFormat("00000.0").format(number).toDouble()
    @Suppress("UNCHECKED_CAST")
    return when (number) {
        is Byte -> formattedNumber.toInt().toByte() as T
        is Short -> formattedNumber.toInt().toShort() as T
        is Int -> formattedNumber.toInt() as T
        is Long -> formattedNumber.toLong() as T
        is Float -> formattedNumber.toFloat() as T
        is Double -> formattedNumber as T
        else -> throw IllegalArgumentException("Unsupported number type")
    }
}

internal fun <T : Number> toTwoDigits(number: T): T {
    val formattedNumber = DecimalFormat("00000.00").format(number).toDouble()
    @Suppress("UNCHECKED_CAST")
    return when (number) {
        is Byte -> formattedNumber.toInt().toByte() as T
        is Short -> formattedNumber.toInt().toShort() as T
        is Int -> formattedNumber.toInt() as T
        is Long -> formattedNumber.toLong() as T
        is Float -> formattedNumber.toFloat() as T
        is Double -> formattedNumber as T
        else -> throw IllegalArgumentException("Unsupported number type")
    }
}

internal fun <T : Number> toThreeDigits(number: T): T {
    val formattedNumber = DecimalFormat("00000.000").format(number).toDouble()
    @Suppress("UNCHECKED_CAST")
    return when (number) {
        is Byte -> formattedNumber.toInt().toByte() as T
        is Short -> formattedNumber.toInt().toShort() as T
        is Int -> formattedNumber.toInt() as T
        is Long -> formattedNumber.toLong() as T
        is Float -> formattedNumber.toFloat() as T
        is Double -> formattedNumber as T
        else -> throw IllegalArgumentException("Unsupported number type")
    }
}

internal fun <T : Number> toFourDigits(number: T): T {
    val formattedNumber = DecimalFormat("00000.0000").format(number).toDouble()
    @Suppress("UNCHECKED_CAST")
    return when (number) {
        is Byte -> formattedNumber.toInt().toByte() as T
        is Short -> formattedNumber.toInt().toShort() as T
        is Int -> formattedNumber.toInt() as T
        is Long -> formattedNumber.toLong() as T
        is Float -> formattedNumber.toFloat() as T
        is Double -> formattedNumber as T
        else -> throw IllegalArgumentException("Unsupported number type")
    }
}

internal fun <T : Number> toFiveDigits(number: T): T {
    val formattedNumber = DecimalFormat("00000.00000").format(number).toDouble()
    @Suppress("UNCHECKED_CAST")
    return when (number) {
        is Byte -> formattedNumber.toInt().toByte() as T
        is Short -> formattedNumber.toInt().toShort() as T
        is Int -> formattedNumber.toInt() as T
        is Long -> formattedNumber.toLong() as T
        is Float -> formattedNumber.toFloat() as T
        is Double -> formattedNumber as T
        else -> throw IllegalArgumentException("Unsupported number type")
    }
}

internal fun <T : Number> toSixDigits(number: T): T {
    val formattedNumber = DecimalFormat("00000.000000").format(number).toDouble()
    @Suppress("UNCHECKED_CAST")
    return when (number) {
        is Byte -> formattedNumber.toInt().toByte() as T
        is Short -> formattedNumber.toInt().toShort() as T
        is Int -> formattedNumber.toInt() as T
        is Long -> formattedNumber.toLong() as T
        is Float -> formattedNumber.toFloat() as T
        is Double -> formattedNumber as T
        else -> throw IllegalArgumentException("Unsupported number type")
    }
}

internal fun <T : Number> toSevenDigits(number: T): T {
    val formattedNumber = DecimalFormat("00000.0000000").format(number).toDouble()
    @Suppress("UNCHECKED_CAST")
    return when (number) {
        is Byte -> formattedNumber.toInt().toByte() as T
        is Short -> formattedNumber.toInt().toShort() as T
        is Int -> formattedNumber.toInt() as T
        is Long -> formattedNumber.toLong() as T
        is Float -> formattedNumber.toFloat() as T
        is Double -> formattedNumber as T
        else -> throw IllegalArgumentException("Unsupported number type")
    }
}

internal fun toTime(number: Number): String {
    val sdf = SimpleDateFormat("HH:mm:ss:SS", Locale.getDefault())
    val time = Calendar.getInstance().apply { timeInMillis = number.toLong() }
    return sdf.format(time.time)
}

internal fun getBitmapFromUri(contentResolver: ContentResolver, uri: Uri): Bitmap? {
    var inputStream: InputStream? = null
    try {
        inputStream = contentResolver.openInputStream(uri)
        if (inputStream != null) {
            return BitmapFactory.decodeStream(inputStream)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        inputStream?.close()
    }
    return null
}

internal fun saveBitmapImageToFile(bitmap: Bitmap, context: Context, name: String): Uri? {
    val imagesFolder = File(context.cacheDir, "images")
    if (!imagesFolder.exists()) {
        imagesFolder.mkdirs()
    }
    val imageFile = File(imagesFolder, "$name.jpg")

    val fos = FileOutputStream(imageFile)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
    fos.flush()
    fos.close()

    return Uri.fromFile(imageFile)
}

internal fun uriToBitmap(contentResolver: ContentResolver, imageUri: Uri): Bitmap? {
    var inputStream: InputStream? = null
    try {
        inputStream = contentResolver.openInputStream(imageUri)
        if (inputStream != null) {
            return BitmapFactory.decodeStream(inputStream)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        inputStream?.close()
    }
    return null
}

internal fun ByteArray.toBitmap(): Bitmap = BitmapFactory.decodeByteArray(this, 0, this.size)

internal fun String.log(tag: String = "Log") =  Log.i(tag, "log: $this")
internal fun Context.toast(m: String) = Toast.makeText(this, "$m", Toast.LENGTH_SHORT).show()


val ioScope = CoroutineScope(Dispatchers.IO)
val mainScope = CoroutineScope(Dispatchers.Main)

internal fun CharSequence?.isNotNullOrEmpty(): Boolean = !this@isNotNullOrEmpty.isNullOrEmpty()

val units = arrayOf("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine")
val teens = arrayOf(
    "Ten",
    "Eleven",
    "Twelve",
    "Thirteen",
    "Fourteen",
    "Fifteen",
    "Sixteen",
    "Seventeen",
    "Eighteen",
    "Nineteen"
)
val tens =
    arrayOf("", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")

internal fun convertToWords(amount: Double): String {
    if (amount == 0.0) {
        return "Zero Rupees"
    }
    return convertAmount(amount).trim().replace(Regex("\\s+"), " ") + " Rupees"
}

internal fun convert(n: Int): String {
    return when {
        n < 10 -> units[n]
        n < 20 -> teens[n - 10]
        else -> {
            val digit1 = n / 100
            val digit2 = n % 100 / 10
            val digit3 = n % 10

            val result = StringBuilder()
            if (digit1 > 0) {
                result.append(units[digit1]).append(" Hundred ")
            }
            when {
                digit2 > 1 -> {
                    result.append(tens[digit2]).append(" ")
                    if (digit3 > 0) {
                        result.append(units[digit3])
                    }
                }

                digit2 == 1 -> {
                    result.append(teens[digit3])
                }

                digit3 > 0 -> {
                    result.append(units[digit3])
                }
            }
            result.toString()
        }
    }
}

internal fun convertAmount(amount: Double): String {
    return when {
        amount < 100 -> convert(amount.toInt())
        amount < 1000 -> convert((amount / 100).toInt()) + " Hundred " + convert(amount.toInt() % 100)
        amount < 100000 -> convert((amount / 1000).toInt()) + " Thousand " + convert(amount.toInt() % 1000)
        amount < 10000000 -> convert((amount / 100000).toInt()) + " Lakh " + convert(amount.toInt() % 100000)
        amount < 1000000000 -> convert((amount / 10000000).toInt()) + " Crore " + convert(amount.toInt() % 10000000)
        else -> "Amount is too large"
    }
}


@SuppressLint("SimpleDateFormat")
internal fun convertMillisToDate(millis: Long?, pattern: String): String {
    return if (millis != null) {
        val dateFormat = SimpleDateFormat(pattern)
        val date = Date(millis)
        dateFormat.format(date)
    } else {
        val currentDate = Date()
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.format(currentDate)
    }
}

