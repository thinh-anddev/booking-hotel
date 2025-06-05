package com.example.booking_hotel.presentation.admin.revenue

import android.annotation.SuppressLint
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
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.booking_hotel.R
import com.example.booking_hotel.presentation.admin.AdminScreen
import com.example.booking_hotel.presentation.admin.AdminViewModel
import com.example.booking_hotel.presentation.admin.components.ButtonFunction
import com.example.booking_hotel.presentation.admin.components.HotelListAdmin
import com.example.booking_hotel.presentation.navgraph.Route
import com.example.booking_hotel.presentation.search.HotelList

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RevenueScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AdminViewModel = hiltViewModel()
) {
    val months = listOf(
        "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4",
        "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8",
        "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
    )
    val years = (2020..2030).toList()
    val listHotel by viewModel.listHotel.observeAsState()
    val revenue by viewModel.selectedRevenue.observeAsState()
    var selectedMonth by remember { mutableStateOf(months[0]) }
    var selectedYear by remember { mutableStateOf(years[0]) }
    var expandedMonth by remember { mutableStateOf(false) }
    var expandedYear by remember { mutableStateOf(false) }
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF4B5842))
                    .padding(horizontal = 20.dp)
                    .padding(top = 50.dp, bottom = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Danh sách khách sạn",
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                        fontSize = 24.sp
                    )
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Column(
                    modifier= Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(top = 20.dp)
                        .padding(horizontal = 10.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    horizontalAlignment = Alignment.Start
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Dropdown chọn tháng
                        Box(modifier=Modifier.weight(1f).clickable { expandedMonth = true }) {
                            OutlinedTextField(
                                value = selectedMonth,
                                onValueChange = {},
                                label = { Text("Chọn tháng") },
                                readOnly = true,
                                trailingIcon = {
                                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                                }
                            )
                            DropdownMenu(
                                expanded = expandedMonth,
                                onDismissRequest = { expandedMonth = false }
                            ) {
                                months.forEach { month ->
                                    DropdownMenuItem(onClick = {
                                        selectedMonth = month
                                        expandedMonth = false
                                    }) {
                                        Text(month)
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        // Dropdown chọn năm
                        Box(modifier=Modifier.weight(1f)
                            .clickable {
                                expandedYear = true
                            }) {
                            OutlinedTextField(
                                value = selectedYear.toString(),
                                onValueChange = {},
                                label = { Text("Chọn năm") },
                                readOnly = true,
                                trailingIcon = {
                                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                                }
                            )
                            DropdownMenu(
                                expanded = expandedYear,
                                onDismissRequest = { expandedYear = false }
                            ) {
                                years.forEach { year ->
                                    DropdownMenuItem(onClick = {
                                        selectedYear = year
                                        expandedYear = false
                                    }) {
                                        Text(year.toString())
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier=Modifier.height(10.dp))
                    Box(modifier = Modifier
                        .weight(0.6f)
                        .padding(horizontal = 10.dp)) {
                        listHotel?.let { hotels ->
                            HotelListAdmin(hotels = hotels) { hotel ->
                                val hotelId = hotel.id
                                val monthIndex = months.indexOf(selectedMonth) + 1
                                viewModel.fetchRevenue(hotelId, monthIndex, selectedYear)
                            }
                        }?: run {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Đang tải danh sách khách sạn...")
                            }
                        }
                    }
                    Box(modifier = Modifier
                        .weight(0.3f)
                        .padding(16.dp)
                        .background(Color.White)
                        .clip(RoundedCornerShape(8.dp))
                        .padding(16.dp)
                    ) {
                        revenue?.let {
                            Column {
                                Text("📊 Doanh thu khách sạn:", fontSize = 18.sp, fontFamily = FontFamily(Font(R.font.lato_bold)))
                                Text("Tháng: ${it.month} / Năm: ${it.year}")
                                Text("Doanh thu: ${"%,.2f".format(it.totalRevenue)} đ")
                            }
                        } ?: Text("Chọn khách sạn để xem doanh thu")
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun RevenueScreenPreview() {
    val navController = rememberNavController()
    RevenueScreen(navController = navController)
}