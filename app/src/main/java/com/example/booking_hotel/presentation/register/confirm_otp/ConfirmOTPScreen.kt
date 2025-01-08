package com.example.booking_hotel.presentation.register.confirm_otp

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.booking_hotel.R
import com.example.booking_hotel.domain.model.User
import com.example.booking_hotel.presentation.register.RegisterViewModel
import com.example.booking_hotel.presentation.register.components.InputText
import com.example.booking_hotel.ui.theme.Background
import com.example.booking_hotel.ui.theme.TextColor

@Composable
fun ConfirmOTPScreen(
    navController: NavController,
    otpCode: String,
    user: User,
    viewModel: RegisterViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
){
    val otpCodeViewModel by viewModel.otpCode.observeAsState()
    val context = LocalContext.current
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_register_screen),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 33.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "nhập mã otp để đăng kí".uppercase(), style = TextStyle(
                    color = TextColor,
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.lato_bold)),
                )
            )
            Spacer(modifier = Modifier.height(45.dp))
            InputText(
                text = otpCodeViewModel!!,
                onValueChange = { viewModel.onOTPCodeChange(it) },
                placeHolder = "Nhập mã otp",
                isPassword = false,
                hasError = false
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                onClick = {
                    if (otpCodeViewModel == otpCode){
                        viewModel.register(navController, user)
                        Toast.makeText(context, "Thành Công", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "sai", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Background, RoundedCornerShape(4.dp)
                    )
                    .padding(vertical = 9.dp)

            ) {
                Text(
                    text = "Tiếp theo", style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.lato_regular)),
                        color = Color.White
                    )
                )
            }
        }
    }
}