package com.example.booking_hotel.presentation.qrcode_scanner

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.booking_hotel.helper.decodeQRCode
import com.example.booking_hotel.helper.getBitmapFromUri
import com.example.booking_hotel.presentation.login.LoginViewModel
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun QRCodeScanner(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = androidx.activity.result.contract.ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val bitmap = getBitmapFromUri(context, it)
            bitmap?.let { bmp ->
                try {
                    val result = decodeQRCode(bmp)
                    if (result != null) {
                        val parts = splitData(result.text)
                        val username = parts[0]
                        val password = parts[1]
                        viewModel.loginByQRCode(navController, username, password)
                        Toast.makeText(context, "QR Code: ${result.text}", Toast.LENGTH_SHORT)
                            .show()

                    } else {
                        Toast.makeText(context, "Failed to decode QR code", Toast.LENGTH_SHORT)
                            .show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "Error decoding QR code", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    val barcodeLauncher = rememberLauncherForActivityResult(
        contract = ScanContract()
    ) { result ->
        if (result.contents == null) {
            Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Scanned: ${result.contents}", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            val options = ScanOptions().apply {
                setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES)
                setPrompt("Scan a barcode")
                setCameraId(0)
                setBeepEnabled(false)
                setBarcodeImageEnabled(true)
            }
            barcodeLauncher.launch(options)
        }) {
            Text("Bắt đầu quét")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            galleryLauncher.launch("image/*")
        }) {
            Text("Chọn ảnh từ thư viện")
        }
    }
}

fun splitData(data: String): List<String> {
    return data.split(" - ")
}
