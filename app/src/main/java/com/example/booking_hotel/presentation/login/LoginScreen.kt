package com.example.booking_hotel.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.booking_hotel.R
import com.example.booking_hotel.helper.ToastText
import com.example.booking_hotel.helper.saveBitmapToGallery
import com.example.booking_hotel.helper.showToast
import com.example.booking_hotel.presentation.login.components.ButtonOtherLogin
import com.example.booking_hotel.presentation.navgraph.Route
import com.example.booking_hotel.presentation.register.components.InputText
import com.example.booking_hotel.ui.theme.Background
import com.example.booking_hotel.ui.theme.TextColor

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val username by viewModel.username
    val password by viewModel.password
    val loginSuccess by viewModel.loginSuccess
    val errorMessage by viewModel.errorMessage
    val qrBitmap by viewModel.qrBitmap.observeAsState()

    var showErrors by remember { mutableStateOf(false) }
    val errorEmail = showErrors && username.isBlank()
    val errorPassword = showErrors && password.isBlank()

    val context = LocalContext.current

    LaunchedEffect(key1 = loginSuccess, key2 = errorMessage) {
        if (loginSuccess) {
            navController.navigate(Route.NavigatorScreen.route) {
                popUpTo(Route.LoginScreen.route) { inclusive = true }
            }
            viewModel.loginSuccess.value = false
        }

        if (errorMessage.isNotEmpty()) {
            errorMessage.showToast(context = context)
        }
    }

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
                text = "đăng nhập".uppercase(), style = TextStyle(
                    color = TextColor,
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.lato_bold)),
                )
            )
            Spacer(modifier = Modifier.height(45.dp))
            InputText(
                text = username,
                onValueChange = { viewModel.onUsernameChange(it) },
                placeHolder = "Tên đăng nhập",
                isPassword = false,
                hasError = errorEmail
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputText(
                text = password,
                onValueChange = { viewModel.onPasswordChange(it) },
                placeHolder = "Password",
                isPassword = true,
                hasError = errorPassword
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Quên mật khẩu?", style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    color = Color(0xFF2C3922)
                ),
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable {
                        ToastText.PENDING_FUNCTION.showToast(context)
                    }
            )
            Spacer(modifier = Modifier.height(35.dp))
            TextButton(
                onClick = {
                    showErrors = true
                    viewModel.login(navController)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Background, RoundedCornerShape(4.dp)
                    )
                    .padding(vertical = 9.dp)

            ) {
                Text(
                    text = "Đăng nhập", style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.lato_regular)),
                        color = Color.White
                    )
                )
            }
            Spacer(modifier = Modifier.height(11.dp))
            Text(
                text = "Hoặc", style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    color = Color(0xFF2C3922)
                )
            )
            Spacer(modifier = Modifier.height(25.dp))
            ButtonOtherLogin(
                methodImage = R.drawable.ic_qrcode,
                onClick = {
                    navController.navigate(Route.QRCodeScanner.route)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            ButtonOtherLogin(
                methodImage = R.drawable.ic_gmail,
                onClick = {
                    ToastText.PENDING_FUNCTION.showToast(context)
                }
            )
            Spacer(modifier = Modifier.height(75.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Bạn chưa có tài khoản?", style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.lato_regular)),
                        color = Color(0xFF2C3922)
                    )
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "Đăng ký",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.lato_regular)),
                        color = Color(0xFF986601)
                    ),
                    modifier = Modifier.clickable {
                        navController.navigate(Route.RegisterScreen.route) {
                            popUpTo(Route.LoginScreen.route) { inclusive = false }
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenP() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}