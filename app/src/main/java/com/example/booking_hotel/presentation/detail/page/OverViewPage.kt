package com.example.booking_hotel.presentation.detail.page

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booking_hotel.R
import com.example.booking_hotel.domain.model.Property
import com.example.booking_hotel.ui.theme.TextColor

@Composable
fun OverviewPage(
    property: Property,
    modifier: Modifier = Modifier
) {
    property?.let { item ->
        val nearbyPlace = item.nearby_places
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = "Giới thiệu khách sạn", style = TextStyle(
                    color = TextColor,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.lato_bold))
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.description, style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Địa điểm lân cận", style = TextStyle(
                    color = TextColor,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.lato_bold))
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier
            ) {
                items(
                    items = if (nearbyPlace.size >= 6) nearbyPlace.take(6) else nearbyPlace,
                ) { nearByPlaceItem ->
                    nearByPlaceItem.transportations.forEach { transportation ->
                        val image = when (transportation.type) {
                            "Taxi" -> R.drawable.ic_taxi
                            "Walking" -> R.drawable.ic_walk
                            "Public transport" -> R.drawable.ic_bus
                            else -> R.drawable.ic_taxi
                        }
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                painterResource(id = image),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${nearByPlaceItem.name ?: "Unknown"} (${transportation.duration ?: "Unknown"})",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.lato_regular))
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}