package com.example.booking_hotel.presentation.account.component

import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.booking_hotel.R
import com.example.booking_hotel.ui.theme.Color_986601
import com.example.booking_hotel.ui.theme.Grey_1

@Composable
fun FieldAccountItem(
    modifier: Modifier = Modifier,
    name: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Grey_1)
            .clickable {
                onClick.invoke()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular))
            ), modifier = Modifier.padding(start = 22.dp)
        )
        Image(painter = painterResource(id = R.drawable.ic_arrow), contentDescription = null)
    }
}

@Composable
fun GlideImage(
    modifier: Modifier = Modifier,
    url: String
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            ImageView(context).apply {
                Glide.with(context).load(url).apply(RequestOptions.circleCropTransform()).into(this)
            }
        }
    )
}

@Composable
fun ButtonChangeImage(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .background(Color_986601)
            .clip(RoundedCornerShape(4.dp))
            .clickable { onClick.invoke() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text, style = TextStyle(
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontSize = 16.sp
            ), modifier = Modifier
                .padding(horizontal = 32.dp)
                .padding(vertical = 16.dp)
        )
    }
}