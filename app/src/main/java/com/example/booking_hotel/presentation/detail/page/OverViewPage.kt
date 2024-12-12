package com.example.booking_hotel.presentation.detail.page

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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booking_hotel.R
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.ui.theme.TextColor

@Composable
fun OverviewPage(
    hotel: Hotel,
    navigateToWeb: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    hotel.let { item ->
        val nearbyPlace = item.nearbyPlaces
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
            ClickableText(
                text = buildAnnotatedString {
                    append("Truy cập để xem chi tiết: ")
                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append(hotel.link ?: "No Link Available")
                    }
                },
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                ),
                onClick = {
                    navigateToWeb(hotel.link!!)
                }
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
                    items = nearbyPlace!!.take(6),
                ) { nearByPlaceItem ->
                    if (nearByPlaceItem.transportations.isNullOrEmpty()) {
                        val defaultImage = R.drawable.ic_taxi
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                painterResource(id = defaultImage),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${nearByPlaceItem.name ?: "Unknown"} (Taxi)",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.lato_regular))
                                )
                            )
                        }
                    } else {
                        nearByPlaceItem.transportations?.forEach { transportation ->
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
                                    text = "${nearByPlaceItem.name ?: "Unknown"} (${transportation.type ?: "Unknown"})",
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
}