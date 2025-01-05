package com.example.booking_hotel.presentation.account.change_information

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import coil.compose.rememberAsyncImagePainter
import com.example.booking_hotel.R
import com.example.booking_hotel.presentation.account.AccountViewModel
import com.example.booking_hotel.presentation.account.component.ButtonChangeImage
import com.example.booking_hotel.presentation.register.components.InputText
import com.example.booking_hotel.ui.theme.Color_4B5842
import com.example.booking_hotel.ui.theme.TextColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeInformationScreen(
    navController: NavController,
    viewModel: AccountViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val avatar by viewModel.avatar.observeAsState()
    val contact by viewModel.contact.observeAsState()
    val email by viewModel.email.observeAsState()
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        Log.d("zzzzz", uri.toString())
        Log.d("zzzzz", avatar.toString())
        viewModel.onImageUriSelected(uri!!)
    }
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
                            text = "Thay đổi thông tin",
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
                    .padding(horizontal = 16.dp)
                    .padding(top = 150.dp)
                    .padding(bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    avatar?.let {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier.size(55.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Log.d("avaaaaaa", avatar.toString())
                                if (it != "") {
                                    Image(
                                        painter = rememberAsyncImagePainter(it),
                                        contentDescription = null
                                    )
                                } else {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_profile),
                                        contentDescription = null
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            ButtonChangeImage(text = "Thay ảnh", onClick = {
                                imagePickerLauncher.launch("image/*")
                            })
                        }
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                    Text(
                        text = "SDT", style = TextStyle(
                            color = Color_4B5842,
                            fontFamily = FontFamily(Font(R.font.lato_bold)),
                            fontSize = 18.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    InputText(
                        text = contact!!,
                        onValueChange = { viewModel.onContactChange(it) },
                        placeHolder = "SDT",
                        isPassword = false,
                        hasError = false
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    Text(
                        text = "Email", style = TextStyle(
                            color = Color_4B5842,
                            fontFamily = FontFamily(Font(R.font.lato_bold)),
                            fontSize = 18.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    InputText(
                        text = email!!,
                        onValueChange = { viewModel.onEmailChange(it) },
                        placeHolder = "Email",
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
                            viewModel.updateUser(navController)
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
}

@Preview(showBackground = true)
@Composable
fun ChangeInformationScreenPreview(

) {
    val navController = rememberNavController()
    ChangeInformationScreen(navController = navController)
}