package com.example.booking_hotel.presentation.admin

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.booking_hotel.R
import com.example.booking_hotel.ui.theme.Grey_1

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdminScreen(
    navController: NavController,
    modifier:Modifier=Modifier
){
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier= Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F9F9))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF4B5842))
                    .padding(horizontal = 20.dp)
                    .padding(top = 50.dp, bottom = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Trang Quản lí",
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                        fontSize = 24.sp
                    )
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier=Modifier.fillMaxWidth().padding(horizontal = 10.dp)
            ) {
                Column(
                    modifier= Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(top = 20.dp)
                        .padding(horizontal = 10.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    horizontalAlignment = Alignment.Start
                ){
                    Text(
                        text = "Top 10 khách sạn có doanh thu cao nhất tháng",
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontSize = 17.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun AdminScreenPreview() {
    val navController = rememberNavController()
    AdminScreen(navController = navController)
}