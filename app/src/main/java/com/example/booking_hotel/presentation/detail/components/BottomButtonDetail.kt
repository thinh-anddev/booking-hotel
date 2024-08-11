package com.example.booking_hotel.presentation.detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booking_hotel.R
import com.example.booking_hotel.presentation.search.components.BottomButtonItem
import com.example.booking_hotel.ui.theme.TextColor

@Composable
fun BottomButtonDetail(
    checkInDate: String,
    numberNight: Int,
    numberPeople: Int,
    items: List<BottomButtonItem>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
            .padding(horizontal = 10.dp)
            .drawBehind {
                val strokeWidth = 1.dp.toPx() // Width of the top border
                drawLine(
                    color = Color.Gray, // Color of the top border
                    start = Offset(0f, 0f), // Start point (top-left corner)
                    end = Offset(size.width, 0f), // End point (top-right corner)
                    strokeWidth = strokeWidth
                )
            }
            .padding(top = 15.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            modifier = Modifier
        ) {
            Image(painterResource(id = items[0].icon), contentDescription = null)
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = checkInDate, style = TextStyle(
                    color = TextColor,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                )
            )
        }
        Row(
            modifier = Modifier
        ) {
            Image(painterResource(id = items[1].icon), contentDescription = null)
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "$numberNight đêm", style = TextStyle(
                    color = TextColor,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                )
            )
        }
        Row(
            modifier = Modifier
        ) {
            Image(painterResource(id = items[2].icon), contentDescription = null)
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "$numberPeople người", style = TextStyle(
                    color = TextColor,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                )
            )
        }
    }
}