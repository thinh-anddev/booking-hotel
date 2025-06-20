package com.example.booking_hotel.presentation.home

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import androidx.lifecycle.asFlow
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.booking_hotel.R
import com.example.booking_hotel.helper.HideSystemBar
import com.example.booking_hotel.helper.ToastText.LESS_INFORMATION
import com.example.booking_hotel.helper.showToast
import com.example.booking_hotel.presentation.common.HotelCardEffect
import com.example.booking_hotel.presentation.dialog.DialogDatePicker
import com.example.booking_hotel.presentation.home.components.CheckWidget
import com.example.booking_hotel.presentation.home.components.NumberPeopleWidget
import com.example.booking_hotel.presentation.home.components.SearchBar
import com.example.booking_hotel.presentation.home.components.SearchButton
import com.example.booking_hotel.presentation.navgraph.Route
import com.example.booking_hotel.ui.theme.Color_757575
import com.example.booking_hotel.ui.theme.TextColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    HideSystemBar()
    val searchQuery by viewModel.searchQuery
    val children by viewModel.children
    val adult by viewModel.adult
    val avatarz by viewModel.avatar.observeAsState()

    val listRecommendHotels = viewModel.recommendedHotels.collectAsLazyPagingItems()

    var showDialogCheckIn by remember { mutableStateOf(false) }
    var showDialogCheckOut by remember { mutableStateOf(false) }
    val checkInDate by viewModel.checkInDate
    val checkOutDate by viewModel.checkOutDate
    val dateState = rememberDatePickerState()
    val dateStateCheckOut = rememberDatePickerState()

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.setCurrentDate()
    }

    dateState.selectedDateMillis?.let {
        viewModel.setSelectedDateMillis(it, true)
    }

    dateStateCheckOut.selectedDateMillis?.let {
        viewModel.setSelectedDateMillis(it, false)
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            if (showDialogCheckIn) {
                DatePickerDialog(
                    onDismissRequest = { showDialogCheckIn = false },
                    confirmButton = {
                        Button(
                            onClick = { showDialogCheckIn = false }
                        ) {
                            Text(text = "Ok")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showDialogCheckIn = false }
                        ) {
                            Text(text = "Hủy")
                        }
                    }
                ) {
                    DatePicker(state = dateState, showModeToggle = true)
                }
            }
            if (showDialogCheckOut) {
                DatePickerDialog(
                    onDismissRequest = { showDialogCheckOut = false },
                    confirmButton = {
                        Button(
                            onClick = { showDialogCheckOut = false }
                        ) {
                            Text(text = "Ok")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showDialogCheckOut = false }
                        ) {
                            Text(text = "Hủy")
                        }
                    }
                ) {
                    DatePicker(state = dateStateCheckOut, showModeToggle = true)
                }
            }
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF4B5842))
                    .padding(horizontal = 20.dp)
                    .padding(top = 50.dp)
                    .padding(bottom = 20.dp)
                    .clickable {
                        navController.navigate(Route.AdminScreen.route)
                    }
            ) {
                val (title, content, avatar) = createRefs()
                Text(
                    text = "Hello Truong Thinh",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.lato_bold))
                    ),
                    modifier = Modifier.constrainAs(title) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                )

                Text(
                    text = "Bạn đang có dự định đi đâu?",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.lato_regular))
                    ),
                    modifier = Modifier.constrainAs(content) {
                        top.linkTo(title.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                    }
                )
                Box(modifier = Modifier.constrainAs(avatar) {
                    end.linkTo(parent.end)
                    top.linkTo(title.top)
                    bottom.linkTo(content.bottom)
                }) {
                    avatarz?.let {
                        if (it != "") {
                            Image(
                                painter = rememberAsyncImagePainter(it),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Image(
                                painterResource(id = R.drawable.ic_profile),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            SearchBar(
                text = searchQuery,
                modifier = Modifier.padding(horizontal = 16.dp),
                placeHolder = "Da lat, Lam Dong",
                onValueChange = { viewModel.onSearchChange(it) },
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                CheckWidget(
                    check = "Check-in",
                    date = checkInDate,
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            showDialogCheckIn = true
                        }
                )
                Spacer(modifier = Modifier.width(20.dp))
                CheckWidget(
                    check = "Check-out",
                    date = checkOutDate,
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            showDialogCheckOut = true
                        }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            NumberPeopleWidget(
                modifier = Modifier.padding(horizontal = 16.dp),
                adults = adult.toString(),
                childrens = children.toString(),
                plusAdult = { viewModel.countAdult(true) },
                plusChildren = { viewModel.countChildren(true) },
                minusAdult = { viewModel.countAdult(false) },
                minusChildren = { viewModel.countChildren(false) }
            )
            Spacer(modifier = Modifier.height(22.dp))
            SearchButton(modifier = Modifier.padding(horizontal = 16.dp), onClick = {
                if (searchQuery == "" || checkInDate == "" || checkOutDate == "") {
                    LESS_INFORMATION.showToast(context = context)
                } else {
                    navController.navigate(
                        route = Route.SearchScreen.passData(
                            searchQuery = searchQuery,
                            checkInDate = checkInDate,
                            checkOutDate = checkOutDate,
                            children = children.toString(),
                            adult = adult.toString()
                        )
                    )
                }
            })
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "Gợi ý khách sạn cho bạn", style = TextStyle(
                    color = TextColor,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.lato_bold))
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            HotelSearchList(
                modifier = Modifier.padding(horizontal = 16.dp),
                properties = listRecommendHotels
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "Tại sao lại lựa chọn Onism?", style = TextStyle(
                    color = TextColor,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.lato_bold))
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            LazyRow(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(
                    start = 7.dp,
                    end = 21.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(
                    count = listExtension.size
                ) {
                    Image(painterResource(id = listExtension[it].image), contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "Đối tác", style = TextStyle(
                    color = TextColor,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.lato_bold))
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(id = R.drawable.img_marvella),
                    contentDescription = null,
                    modifier = Modifier.size(104.dp)
                )
                Divider(
                    color = Color_757575,
                    modifier = Modifier
                        .height(62.dp)
                        .width(1.dp)
                )
                Image(
                    painterResource(id = R.drawable.img_marvella),
                    contentDescription = null,
                    modifier = Modifier.size(104.dp)
                )
                Divider(
                    color = Color_757575,
                    modifier = Modifier
                        .height(62.dp)
                        .width(1.dp)
                )
                Image(
                    painterResource(id = R.drawable.img_marvella),
                    contentDescription = null,
                    modifier = Modifier.size(104.dp)
                )
                Divider(
                    color = Color_757575,
                    modifier = Modifier
                        .height(62.dp)
                        .width(1.dp)
                )
                Image(
                    painterResource(id = R.drawable.img_marvella),
                    contentDescription = null,
                    modifier = Modifier.size(104.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
//    val navController = rememberNavController()
//    HomeScreen(navController = navController)
}