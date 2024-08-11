package com.example.booking_hotel.presentation.detail.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booking_hotel.R
import com.example.booking_hotel.ui.theme.TextColor
import com.example.booking_hotel.ui.theme.TitleColor

@Composable
fun PolicyPage(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 15.dp)
            .padding(horizontal = 15.dp)
    ) {
        Text(
            text = "Chính sách chung", style = TextStyle(
                color = TextColor,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.lato_bold))
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Thời gian nhận phòng/trả phòng", style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular))
            )
        )
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Giờ nhận phòng:", style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                )
            )
            Text(text = "Từ 14:00", style = TextStyle(
                color = TitleColor,
                fontFamily = FontFamily(Font(R.font.lato_bold)),
                fontSize = 14.sp
            ))
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Giờ trả phòng:", style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                )
            )
            Text(text = "Trước 12:00", style = TextStyle(
                color = TitleColor,
                fontFamily = FontFamily(Font(R.font.lato_bold)),
                fontSize = 14.sp
            ))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Quy định đặt chỗ", style = TextStyle(
                color = TextColor,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.lato_bold))
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Bạn có 24 tiếng đặt chỗ và thanh toán chuyển khoản 100% số tiền phòng\n" +
                    "Huỷ phòng trước ngày check-in 12 tiếng nếu sau khoản thời gian đó bạn không thể huỷ phòng.\n" +
                    "Thông tin đặt chỗ, hoá đơn thanh toán sẽ được gửi vào Email của bạn, xuất trình hoá đơn khi nhận phòng.", style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular))
            )
        )
    }
}