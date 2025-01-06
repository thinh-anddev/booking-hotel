package com.example.booking_hotel.helper

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatReader
import com.google.zxing.MultiFormatWriter
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.Result
import com.google.zxing.common.BitMatrix
import com.google.zxing.common.HybridBinarizer
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun generateQRCodeBitmap(data: String): Bitmap {
    val writer = MultiFormatWriter()
    val bitMatrix: BitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512)

    val encoder = BarcodeEncoder()
    return encoder.createBitmap(bitMatrix)
}
fun saveBitmapToGallery(context: Context, bitmap: Bitmap) {
    try {
        val picturesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val fileName = "QR_Code_${SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(
            Date()
        )}.png"
        val file = File(picturesDirectory, fileName)

        FileOutputStream(file).use { outStream ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream)
            outStream.flush()
        }
        context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)))

    } catch (e: IOException) {
        e.printStackTrace()
    }
}
fun getBitmapFromUri(context: Context, uri: Uri): Bitmap? {
    return try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun decodeQRCode(bitmap: Bitmap): Result? {
    val width = bitmap.width
    val height = bitmap.height
    val pixels = IntArray(width * height)

    bitmap.getPixels(pixels, 0, width, 0, 0, width, height)
    val luminanceSource = com.google.zxing.RGBLuminanceSource(width, height, pixels)
    val binaryBitmap = com.google.zxing.BinaryBitmap(HybridBinarizer(luminanceSource))
    val multiFormatReader = MultiFormatReader()
    return try {
        multiFormatReader.decode(binaryBitmap)
    } catch (e: Exception) {
        null
    }
}