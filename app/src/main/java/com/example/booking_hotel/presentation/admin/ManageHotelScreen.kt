package com.example.booking_hotel.presentation.admin
import android.annotation.SuppressLint
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
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Scaffold
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.booking_hotel.R
import com.example.booking_hotel.presentation.admin.components.ButtonFunction

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ManageHotelScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AdminViewModel = hiltViewModel()
) {
    val hotelName = remember { mutableStateOf("") }
    val listHotel by viewModel.listHotel.observeAsState(initial = emptyList())
    var expandedMenuIndex by remember { mutableStateOf(-1) }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F9F9))
        ) {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF4B5842))
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Quản lý khách sạn",
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                        fontSize = 24.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                listHotel.forEachIndexed { index, hotel ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .clickable {
                                expandedMenuIndex = if (expandedMenuIndex == index) -1 else index
                            }
                            .padding(16.dp)
                            .padding(bottom = 4.dp)
                    ) {
                        Column {
                            Text(
                                text = hotel.name!!,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.lato_bold))
                            )
                            if (expandedMenuIndex == index) {
                                Spacer(modifier = Modifier.height(8.dp))
                                DropdownMenu(
                                    expanded = true,
                                    onDismissRequest = { expandedMenuIndex = -1 }
                                ) {
                                    DropdownMenuItem(onClick = {
//                                        viewModel.deleteHotel(hotel.id)
                                        expandedMenuIndex = -1
                                    }) {
                                        Text("🗑 Xóa khách sạn", color = Color.Red)
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
