package com.example.booking_hotel.presentation.confirm_order

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.booking_hotel.R
import com.example.booking_hotel.helper.generateOrderCode
import com.example.booking_hotel.presentation.register.components.InputText
import com.example.booking_hotel.ui.theme.Color_986601
import com.example.booking_hotel.ui.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ConfirmOrderScreen(
    navController: NavController,
    checkInDate: String,
    checkOutDate: String,
    numberNight: Int,
    price: Double,
    modifier: Modifier = Modifier
) {
    val genOrderCode = generateOrderCode()
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Xác nhận đặt phòng",
                            style = TextStyle(
                                color = Color.White,
                                fontFamily = FontFamily(Font(R.font.lato_bold)),
                                fontSize = 24.sp
                            ),
                            modifier = Modifier.padding(end = 30.dp)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Image(painterResource(id = R.drawable.ic_back_1), contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = TextColor)
            )
        }
    ) {
        Column(
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 50.dp)
                    .padding(bottom = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "1. Thông tin của bạn",
                    style = TextStyle(
                        color = TextColor,
                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(top = 35.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                InputText(
                    text = name,
                    onValueChange = { name = it },
                    placeHolder = "Họ và tên",
                    isPassword = false,
                    hasError = false
                )
                Spacer(modifier = Modifier.height(10.dp))
                InputText(
                    text = phone,
                    onValueChange = { phone = it },
                    placeHolder = "SDT",
                    isPassword = false,
                    hasError = false
                )
                Spacer(modifier = Modifier.height(10.dp))
                InputText(
                    text = email,
                    onValueChange = { email = it },
                    placeHolder = "Email",
                    isPassword = false,
                    hasError = false
                )
                Text(
                    text = "2. Chi tiết đặt phòng",
                    style = TextStyle(
                        color = TextColor,
                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(top = 30.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Ngày nhận phòng: ",
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontSize = 16.sp
                        )
                    )
                    Row(
                        modifier = Modifier
                    ) {
                        Text(
                            text = "14:00 ngày ",
                            style = TextStyle(
                                color = Color.Black,
                                fontFamily = FontFamily(Font(R.font.lato_regular)),
                                fontSize = 16.sp
                            )
                        )
                        Text(
                            text = checkInDate,
                            style = TextStyle(
                                color = Color_986601,
                                fontFamily = FontFamily(Font(R.font.lato_regular)),
                                fontSize = 16.sp
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Ngày trả phòng: ",
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontSize = 16.sp
                        )
                    )
                    Row(
                        modifier = Modifier
                    ) {
                        Text(
                            text = "12:00 ngày ",
                            style = TextStyle(
                                color = Color.Black,
                                fontFamily = FontFamily(Font(R.font.lato_regular)),
                                fontSize = 16.sp
                            )
                        )
                        Text(
                            text = checkOutDate,
                            style = TextStyle(
                                color = Color_986601,
                                fontFamily = FontFamily(Font(R.font.lato_regular)),
                                fontSize = 16.sp
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Số đêm: ",
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = "$numberNight",
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontSize = 16.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Tổng: ",
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = "${price.toInt()}đ",
                        style = TextStyle(
                            color = Color_986601,
                            fontFamily = FontFamily(Font(R.font.lato_bold)),
                            fontSize = 20.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Mã đặt chỗ: ",
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = genOrderCode,
                        style = TextStyle(
                            color = Color.Red,
                            fontFamily = FontFamily(Font(R.font.lato_bold)),
                            fontSize = 17.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Thông tin thanh toán: ",
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.lato_regular)),
                        fontSize = 16.sp
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier.padding(start = 72.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Ngân hàng TPBank ",
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Black,
                                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                                    fontSize = 16.sp
                                )
                            ) {
                                append("STK ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color_986601,
                                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                                    fontSize = 16.sp
                                )
                            ) {
                                append("30984165517")
                            }
                        }, modifier = Modifier.padding(top = 7.dp)
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Black,
                                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                                    fontSize = 16.sp
                                )
                            ) {
                                append("Nội dung ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color_986601,
                                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                                    fontSize = 16.sp
                                )
                            ) {
                                append("HoTen_MaDatCho")
                            }
                        }, modifier = Modifier.padding(top = 7.dp)
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .padding(horizontal = 15.dp)
                        .background(TextColor),
                ) {
                    Text(
                        text = "Xác nhận",
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontSize = 16.sp
                        ), modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmOrderScreenPreview() {
    var navController = rememberNavController()
    ConfirmOrderScreen(
        navController,
        "asd", "asda", 1, 0.0
    )
}
