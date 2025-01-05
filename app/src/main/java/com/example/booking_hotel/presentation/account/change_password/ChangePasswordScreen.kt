package com.example.booking_hotel.presentation.account.change_password

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.booking_hotel.R
import com.example.booking_hotel.helper.ToastText
import com.example.booking_hotel.presentation.account.AccountViewModel
import com.example.booking_hotel.presentation.register.components.InputText
import com.example.booking_hotel.ui.theme.Color_986601
import com.example.booking_hotel.ui.theme.TextColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(
    navController: NavController,
    viewModel: AccountViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val password by viewModel.password.observeAsState()
    val confirmPassword by viewModel.confirmPassword.observeAsState()
    val newPassword by viewModel.newPassword.observeAsState()
    val errorValue by viewModel.errorValue.observeAsState()
    val context = LocalContext.current
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
                            text = "Đặt lại mật khẩu",
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
                .padding(horizontal = 16.dp)
                .padding(top = 150.dp)
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painterResource(id = R.drawable.logo_app), contentDescription = null)

                Text(
                    text = "Good trip, good day!", style = TextStyle(
                        color = TextColor,
                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                        fontSize = 26.sp
                    )
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(18.dp))
                InputText(
                    text = password!!,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    placeHolder = "Nhập mật khẩu cũ",
                    isPassword = false,
                    hasError = false
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Quên mật khẩu?",
                    textAlign = TextAlign.End,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.lato_regular)),
                        fontSize = 16.sp,
                        color = Color_986601
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(30.dp))
                InputText(
                    text = newPassword!!,
                    onValueChange = { viewModel.onNewPasswordChange(it) },
                    placeHolder = "Nhập mật khẩu mới",
                    isPassword = false,
                    hasError = false
                )
                Spacer(modifier = Modifier.height(18.dp))
                InputText(
                    text = confirmPassword!!,
                    onValueChange = { viewModel.onConfirmPasswordChange(it) },
                    placeHolder = "Xác nhận mật khẩu mới",
                    isPassword = false,
                    hasError = false
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(TextColor)
                    .clickable {
                        viewModel.changePassword(navController)
                        Toast.makeText(context, errorValue, Toast.LENGTH_SHORT).show()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Xác nhận",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.lato_regular)),
                        color = Color.White
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordPreview() {
    val navController = rememberNavController()
    ChangePasswordScreen(navController = navController)
}