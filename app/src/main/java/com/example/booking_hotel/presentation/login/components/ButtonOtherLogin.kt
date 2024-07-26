package com.example.booking_hotel.presentation.login.components

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booking_hotel.R

@Composable
fun ButtonOtherLogin(
    @DrawableRes methodImage: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(0xFF2C3922),
                shape = RoundedCornerShape(4.dp)
            )
            .background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(
            onClick = onClick,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Đăng nhập bằng",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    color = Color(0xFF2C3922)
                ),
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(12.dp))
            Image(painterResource(id = methodImage), contentDescription = null)
        }
    }
}

@Preview
@Composable
fun asd() {
    ButtonOtherLogin(methodImage = R.drawable.ic_facebook, onClick = { /*TODO*/ })
}