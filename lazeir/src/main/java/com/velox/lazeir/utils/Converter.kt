package com.velox.lazeir.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi
import java.io.ByteArrayOutputStream


/**
 * [toByteArray] Converting Bitmap to ByteArray
 * */
fun Bitmap.toByteArray(): ByteArray {
    val stream = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}

fun String.toBitMapFromBase64(): Bitmap {
    val decodedBytes = Base64.decode(this, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
}
/**
 * [toByteArray] Converting Uri to ByteArray
 * */
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
fun ByteArray.toBase64(): String = String(java.util.Base64.getEncoder().encode(this))

fun String.toEncodedBase64(): String{
    return ""
}

fun String.toDecodedBase64(): String{
    return ""
}
