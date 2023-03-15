package com.velox.lazeir.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import java.io.ByteArrayOutputStream

fun convertBitmapToByteArray(bitmap: Bitmap): ByteArray? {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}

@SuppressLint("Recycle")
fun convertUriToByteArray(context: Context, uri: Uri?): ByteArray? {
    if (uri == null) {
        return null
    }
    val iStream = context.contentResolver.openInputStream(uri)

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