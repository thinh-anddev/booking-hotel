package com.example.booking_hotel.presentation.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.booking_hotel.presentation.navgraph.Route
import com.example.booking_hotel.presentation.register.components.InputText
import com.example.booking_hotel.ui.theme.Background
import com.example.booking_hotel.ui.theme.TextColor

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val username by viewModel.username
    val email by viewModel.email
    val contact by viewModel.contact
    val password by viewModel.password
    val confirmPassword by viewModel.confirmPassword
    val errorMessage by viewModel.errorMessage

    var showErrors by remember { mutableStateOf(false) }

    val errorUsername = showErrors && username.isBlank()
    val errorEmail = showErrors && email.isBlank()
    val errorContact = showErrors && contact.isBlank()
    val errorPassword = showErrors && password.isBlank()
    val errorConfirm = showErrors && confirmPassword.isBlank()

    val context = LocalContext.current

    LaunchedEffect(key1 = errorMessage) {
        if (errorMessage.isNotEmpty()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
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
                text = "Đăng ký".uppercase(), style = TextStyle(
                    color = TextColor,
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.lato_bold)),
                )
            )
            Spacer(modifier = Modifier.height(32.dp))
            InputText(
                text = username,
                onValueChange = { viewModel.onUsernameChange(it) },
                placeHolder = "Username",
                isPassword = false,
                hasError = errorUsername
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputText(
                text = email,
                onValueChange = { viewModel.onEmailChange(it) },
                placeHolder = "Email",
                isPassword = false,
                hasError = errorEmail
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputText(
                text = contact,
                onValueChange = { viewModel.onContactChange(it) },
                placeHolder = "SDT",
                isPassword = false,
                hasError = errorContact
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputText(
                text = password,
                onValueChange = { viewModel.onPasswordChange(it) },
                placeHolder = "Mật khẩu",
                isPassword = true,
                hasError = errorPassword
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputText(
                text = confirmPassword,
                onValueChange = { viewModel.onConfirmPasswordChange(it) },
                placeHolder = "Xác nhận mật khẩu",
                isPassword = true,
                hasError = errorConfirm
            )
            Spacer(modifier = Modifier.height(90.dp))
            TextButton(
                onClick = {
                    showErrors = true
                    //viewModel.register(navController)
                    viewModel.sendOTP(navController)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Background, RoundedCornerShape(4.dp)
                    )
                    .padding(vertical = 9.dp)

            ) {
                Text(
                    text = "Đăng ký", style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.lato_regular)),
                        color = Color.White
                    )
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Bạn đã có tài khoản?", style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.lato_regular)),
                        color = Color(0xFF2C3922)
                    )
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "Đăng nhập",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.lato_regular)),
                        color = Color(0xFF986601)
                    ),
                    modifier = Modifier.clickable {
                        navController.navigate(Route.LoginScreen.route) {
                            popUpTo(0) {inclusive = false}
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenprevuew() {
    val navController = rememberNavController()
    RegisterScreen(navController = navController)
}