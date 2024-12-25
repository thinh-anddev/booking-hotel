package com.example.booking_hotel.presentation.detail.page

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booking_hotel.R
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.ui.theme.TextColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RatePage(
    hotel: Hotel,
    viewModel: PageViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val avgRating by viewModel.avgRating.observeAsState()
    val totalRate by viewModel.totalRate.observeAsState()
    Log.d("totalRate", totalRate.toString())
    val countStar by viewModel.countStar.observeAsState()

    LaunchedEffect(hotel.id) {
        viewModel.getAvgRating(hotel.id)
        viewModel.getTotalRate(hotel.id)
        viewModel.getCountStar(hotel.id)
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .padding(horizontal = 15.dp)
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = "Đánh giá", style = TextStyle(
                    color = TextColor,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.lato_bold))
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 17.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF9F6F1))
                        .border(
                            width = 1.dp,
                            color = TextColor,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = avgRating.toString(), style = TextStyle(
                            color = TextColor,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.lato_bold))
                        )
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Log.d("RatePage", "totalRate: $totalRate, countStar: ${countStar.toString()}")
                    if (totalRate != null && countStar != null) {
                        Column {
                            (5 downTo 1).forEach { star ->
                                ProgressBarCustom(
                                    maxVal = totalRate!!,
                                    currentVal = countStar?.get(star) ?: 1,
                                    title = "$star sao"
                                )
                            }
                        }
                    } else {
                        Text("Đang tải dữ liệu...")
                    }
                }
            }
        }
    }
}

@Composable
fun ProgressBarCustom(
    maxVal: Int,
    currentVal: Int,
    title: String,
    modifier: Modifier = Modifier
) {
    val progress = currentVal.toFloat() / maxVal.toFloat()
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title, style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular))
            ), modifier = Modifier.weight(1f)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
                .height(8.dp)
                .border(width = 1.dp, color = TextColor)
                .weight(4f)
        ) {
            LinearProgressIndicator(
                progress = progress,
                color = TextColor,
                trackColor = Color.White,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Text(
            text = currentVal.toString(), style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular))
            ), modifier = Modifier.padding(start = 16.dp)
        )
    }
}