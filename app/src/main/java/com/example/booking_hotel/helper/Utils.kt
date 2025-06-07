package com.example.booking_hotel.helper

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

fun getRealPathFromUri(context: Context, uri: Uri): String? {
    var result: String? = null
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    if (cursor != null && cursor.moveToFirst()) {
        val idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
        result = if (idx >= 0) cursor.getString(idx) else null
        cursor.close()
    }
    return result
}
fun prepareFilePart(uri: Uri, context: Context): MultipartBody.Part {
    val file = File(getRealPathFromUri(context, uri)!!)
    val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData("file", file.name, requestFile)
}
fun uriToFile(uri: Uri, context: Context): File {
    val inputStream = context.contentResolver.openInputStream(uri)
    val file = File(context.cacheDir, "avatar_${System.currentTimeMillis()}.jpg")
    val outputStream = FileOutputStream(file)
    inputStream?.copyTo(outputStream)
    inputStream?.close()
    outputStream.close()
    return file
}