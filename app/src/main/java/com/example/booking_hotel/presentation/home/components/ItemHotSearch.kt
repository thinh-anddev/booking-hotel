package com.example.booking_hotel.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.booking_hotel.R
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.ui.theme.TitleColor

@Composable
fun ItemHotSearch(
    modifier: Modifier = Modifier,
    hotel: Hotel? = null
) {
    hotel?.let {
        val context = LocalContext.current
        val images = it.images
        val ratePerNightX = it.ratePerNight
        Card(
            modifier = modifier
                .width(225.dp)
                .height(276.dp)
                .clip(RoundedCornerShape(4.dp)), elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(
                modifier = Modifier
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context).data(images?.get(0)?.originalImage).build(),
                    contentDescription = null,
                    modifier = Modifier.weight(4f),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .weight(6f)
                        .padding(horizontal = 13.dp)
                ) {
                    Text(
                        text = it.name ?: "", style = TextStyle(
                            color = TitleColor,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.lato_bold))
                        )
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = it.name ?: "", style = TextStyle(
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontStyle = FontStyle.Italic
                        )
                    )
                    Spacer(modifier = Modifier.height(11.dp))
                    Text(
                        text = "it.description" ?: "No description", style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(11.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "giá chỉ từ", style = TextStyle(
                                color = Color(0xFF4B5842),
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.lato_regular)),
                                fontStyle = FontStyle.Italic
                            )
                        )
                        Text(
                            text = ratePerNightX?.lowest ?: "N/A", style = TextStyle(
                                color = TitleColor,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.lato_bold)),
                            )
                        )
                    }
                }
            }
        }

    } ?: run {
        // Handle the case where property is null, maybe show a default UI or an error message
        Text(text = "No property available")
    }
}