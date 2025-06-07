package com.example.booking_hotel.presentation.account

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.booking_hotel.R
import com.example.booking_hotel.presentation.account.component.FieldAccountItem
import com.example.booking_hotel.presentation.navgraph.Route
import com.example.booking_hotel.ui.theme.TextColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AccountViewModel = hiltViewModel()
) {
    val context= LocalContext.current
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val name by viewModel.name.observeAsState()
        val contact by viewModel.contact.observeAsState()
        val avatarDisplay by viewModel.avatarDisplay.observeAsState()
        val imagePickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            viewModel.onImageUriSelected(uri!!)
        }
        Column(
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF4B5842))
                    .padding(horizontal = 20.dp)
                    .padding(top = 50.dp)
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hồ sơ của tôi", style = TextStyle(
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                        fontSize = 24.sp
                    )
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
            ) {
                val (avatarLayout, nameLayout, contactLayout) = createRefs()
                avatarDisplay?.let {
                    Log.d("avatar", avatarDisplay.toString())
                    Column(
                        modifier = Modifier
                            .size(55.dp)
                            .constrainAs(avatarLayout) {
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom)
                            }

                    ) {
                        if (it != "") {
                            Image(
                                painter = rememberAsyncImagePainter(it),
                                contentDescription = null
                            )
                        } else {
                            Image(painter = painterResource(id = R.drawable.ic_profile), contentDescription = null)
                        }
                    }
                }
                Text(
                    text = name!!,
                    style = TextStyle(
                        color = TextColor,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.lato_bold))
                    ),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .padding(bottom = 3.dp)
                        .constrainAs(nameLayout) {
                            start.linkTo(avatarLayout.end)
                            top.linkTo(avatarLayout.top)
                        }
                )
                Text(
                    text = contact ?: "Unknown",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.lato_regular))
                    ),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .padding(top = 3.dp)
                        .constrainAs(contactLayout) {
                            start.linkTo(avatarLayout.end)
                            top.linkTo(nameLayout.bottom)
                        }
                )
            }
            Spacer(modifier = Modifier.height(45.dp))
            Column(
                modifier = Modifier.padding(horizontal = 25.dp)
            ) {
                FieldAccountItem(name = "Thay đổi thông tin", onClick = {
                    navController.navigate(Route.ChangeInformationScreen.route)
                })
                Spacer(modifier = Modifier.height(16.dp))
                FieldAccountItem(name = "Đặt lại mật khẩu", onClick = {
                    navController.navigate(Route.ChangePasswordScreen.route)
                })
                Spacer(modifier = Modifier.height(16.dp))
                FieldAccountItem(name = "Liên hệ", onClick = {
                    navController.navigate(Route.ContactScreen.route)
                })
                Spacer(modifier = Modifier.height(16.dp))
                FieldAccountItem(name = "Tạo QR đăng nhập nhanh", onClick = {
                    navController.navigate(Route.QRCodeScreen.route)
                })
                Spacer(modifier = Modifier.height(16.dp))
                FieldAccountItem(name = "Đăng xuất", onClick = {
                    viewModel.logout(navController = navController)
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    val navController = rememberNavController()
    AccountScreen(navController = navController)
}