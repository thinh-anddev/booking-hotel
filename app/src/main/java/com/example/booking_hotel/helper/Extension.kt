package com.example.booking_hotel.helper

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Date


fun String.showToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

@Composable
fun HideSystemBar() {
    val context = LocalContext.current
    val window = (context as Activity).window

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
    } else {
        window?.decorView?.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )
    }
}
fun generateOrderCode(): String {
    val currentTime = System.currentTimeMillis()
    val dateFormat = SimpleDateFormat("yyyyMMddHHmmss")
    val formattedTime = dateFormat.format(Date(currentTime))
    val randomSuffix = (100..999).random()
    return "ORD-$formattedTime-$randomSuffix"
}

