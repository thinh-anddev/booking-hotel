package com.example.booking_hotel.presentation.ordered.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.booking_hotel.R
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.domain.model.Order
import com.example.booking_hotel.helper.Constant
import com.example.booking_hotel.helper.formatDate
import com.example.booking_hotel.presentation.ordered.OrderViewModel
import com.example.booking_hotel.ui.theme.Color_986601
import com.example.booking_hotel.ui.theme.ContentColor
import com.example.booking_hotel.ui.theme.TextColor

@Composable
fun ItemOrder(
    order: Order,
    viewModel: OrderViewModel = hiltViewModel(),
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var hotel by remember { mutableStateOf<Hotel?>(null) }
    val nameFunction by viewModel.nameFunction.observeAsState()
    val backgroundFunction by viewModel.backgroundColor.observeAsState()
    val context = LocalContext.current
    val animate = hotel?.amenities
    val newStatus = when (order.orderStatus) {
        Constant.PENDING -> Constant.PAID
        Constant.PAID -> Constant.CANCELED
        else -> ""
    }

    LaunchedEffect(order.hotelId) {
        try {
            hotel = viewModel.getHotelById(order.hotelId)
            Log.d("ItemOrder", "Hotel fetched: $hotel")
        } catch (e: Exception) {
            Log.e("ItemOrder", "Error fetching hotel: $e")
        }
    }
    LaunchedEffect(key1 = order.orderStatus) {
        viewModel.syncDataBackground(order.orderStatus)
        viewModel.syncDataStatus(order.orderStatus)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFF9F6F1))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 13.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = hotel?.name ?: "", style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                        color = Color_986601
                    )
                )
                Text(
                    text = formatDate(order.dateCreated), style = TextStyle(
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.lato_regular))
                    )
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(if (hotel?.images != null) hotel!!.images?.get(0) else "")
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .width(135.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shadow(8.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .padding(start = 10.dp)
                ) {
                    Row(
                        modifier = Modifier

                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_bed),
                                contentDescription = null
                            )
                            Text(
                                text = "1 giường", style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                                    color = Color.Black
                                )
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_user),
                                contentDescription = null
                            )
                            Text(
                                text = "${order.numberPeople} khách", style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                                    color = Color.Black
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Tiện nghi", style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.lato_bold)),
                            fontSize = 16.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        items(count = 4) { index ->
                            val ani = animate?.getOrElse(index) { listOf("", "") }
                            Text(
                                text = "- $ani",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color.Black
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Giá phòng", style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp
                        ), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "${order.totalPrice} đ", style = TextStyle(
                            color = Color_986601,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.lato_bold))
                        ), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .height(50.dp)
                .width(150.dp)
                .align(Alignment.End)
                .background(backgroundFunction!!)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    onClick.invoke(newStatus)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = nameFunction!!, style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.lato_bold)),
                    fontSize = 16.sp,
                    color = Color.White
                )
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ItemOrderPreview() {
//    ItemOrder()
//}