package com.example.booking_hotel.presentation.account.qrcode

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booking_hotel.R
import com.example.booking_hotel.helper.saveBitmapToGallery
import com.example.booking_hotel.ui.theme.TextColor

@Composable
fun QRCodeScreen(
    viewModel: QRCodeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val qrBitmap by viewModel.bitmap.observeAsState()
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        qrBitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.clickable {
                })
            TextButton(
                onClick = {
                    saveBitmapToGallery(context, it)
                    Toast.makeText(context, "Đã tải xuống", Toast.LENGTH_SHORT).show()
                },
                modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .padding(horizontal = 15.dp)
                    .background(TextColor),
            ) {
                Text(
                    text = "Tải về",
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.lato_regular)),
                        fontSize = 16.sp
                    ), modifier = Modifier.padding(vertical = 10.dp)
                )
            }
        }
    }
}