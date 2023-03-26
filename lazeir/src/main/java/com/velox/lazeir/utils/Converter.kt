package com.velox.lazeir.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import java.io.ByteArrayOutputStream


/**
 * Converting Bitmap to ByteArray
 * */
fun Bitmap.toByteArray(): ByteArray {
    val stream = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}

/**
 * Converting Uri to ByteArray
 * */
@SuppressLint("Recycle")
fun Uri.toByteArray(context: Context): ByteArray? {
    val iStream = context.contentResolver.openInputStream(this)
    val byteBuffer = ByteArrayOutputStream()
    val bufferSize = 1024
    val buffer = ByteArray(bufferSize)
    var len = 0
    return if (iStream != null) {
        while (iStream.read(buffer).also { len = it } != -1) {
            byteBuffer.write(buffer, 0, len)
        }
        byteBuffer.toByteArray()
    } else {
        null
    }
}
