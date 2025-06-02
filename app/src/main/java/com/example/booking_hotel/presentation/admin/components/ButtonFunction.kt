package com.example.booking_hotel.presentation.admin.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booking_hotel.R
import com.example.booking_hotel.ui.theme.TextColor

@Composable
fun ButtonFunction(
    functionName:String,
    modifier: Modifier=Modifier,
    onClick:()->Unit
){
    Row(
        modifier = modifier
            .background(TextColor)
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(4.dp)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(
            onClick = onClick,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = functionName, style = TextStyle(
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    fontSize = 16.sp
                )
            )
        }
    }
}