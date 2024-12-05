package com.example.booking_hotel.presentation.detail

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.booking_hotel.R
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.presentation.detail.components.BottomButtonDetail
import com.example.booking_hotel.presentation.detail.components.PageIndicatorDetail
import com.example.booking_hotel.presentation.detail.page.OverviewPage
import com.example.booking_hotel.presentation.detail.page.PolicyPage
import com.example.booking_hotel.presentation.detail.page.RatePage
import com.example.booking_hotel.presentation.detail.page.RoomTypePage
import com.example.booking_hotel.presentation.navgraph.Route
import com.example.booking_hotel.presentation.search.components.listBottomButtonDetail
import com.example.booking_hotel.ui.theme.TextColor
import com.example.booking_hotel.ui.theme.TitleColor
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    hotel: Hotel
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        bottomBar = {
            BottomButtonDetail(
                checkInDate = "1/8/2003",
                numberNight = 3,
                numberPeople = 4,
                items = listBottomButtonDetail
            )
        }
    ) {
        val scrollState = rememberScrollState()
        val context = LocalContext.current
        val images = hotel.images
        val page = listOf(
            images?.get(0),
            images?.get(1),
            images?.get(2)
        )
        val tabs = listOf(
            "Tổng quan",
            "Loại phòng",
            "Chính sách",
            "Đánh giá"
        )
        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState(
            pageCount = {
                tabs.size
            }
        )
        val imgState = rememberPagerState(
            initialPage = 0
        ) {
            page.size
        }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .statusBarsPadding()
        ) {
            val (image, header, title, body) = createRefs()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                    }
            ) {
                HorizontalPager(
                    state = imgState, modifier = Modifier
                        .fillMaxWidth()
                        .align(
                            Alignment.Center
                        )
                ) { index ->
                    ImagePage(
                        images = page[index]!!, context = context, modifier = Modifier
                    )
                }
                PageIndicatorDetail(
                    pageSize = page.size,
                    selectedPage = imgState.currentPage,
                    selectedBackground = R.drawable.ic_dot_selected,
                    unSelectedBackground = R.drawable.ic_dot_unselected,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 10.dp)
                )
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
                    .constrainAs(header) {
                        top.linkTo(parent.top)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Image(painterResource(id = R.drawable.ic_back_1), contentDescription = null)
                }
                IconButton(onClick = {
                    navController.navigate(Route.NavigatorScreen.route) {
                        popUpTo(Route.DetailScreen.route) { inclusive = true }
                    }
                }) {
                    Image(
                        painterResource(id = R.drawable.ic_home_unselected),
                        contentDescription = null
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .padding(top = 30.dp)
                    .padding(bottom = 10.dp)
                    .constrainAs(title) {
                        top.linkTo(image.bottom)
                    }
            ) {
                Text(
                    text = hotel.name ?: "", style = TextStyle(
                        color = TextColor,
                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                        fontSize = 24.sp
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = hotel.name ?: "Unknown", style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontSize = 18.sp,
                            fontStyle = FontStyle.Italic
                        )
                    )
                    Text(
                        text = "3000+ lượt đánh giá", style = TextStyle(
                            color = TitleColor,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Italic
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(body) {
                        top.linkTo(title.bottom)
                    }
            ) {
                ConstraintLayout(
                    modifier = Modifier
                ) {
                    val (tab, line) = createRefs()
                    TabRow(
                        selectedTabIndex = pagerState.currentPage
                    ) {
                        tabs.forEachIndexed { index, title ->
                            val iconVisible = pagerState.currentPage == index
                            Tab(
                                selected = pagerState.currentPage == index,
                                onClick = {
                                    scope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                },
                                modifier = Modifier
                                    .constrainAs(tab) {
                                        top.linkTo(parent.top)
                                    }
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
                                ) {
                                    Text(
                                        text = title,
                                        style = TextStyle(
                                            color = TextColor,
                                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                                            fontSize = 13.sp
                                        ),
                                        modifier = Modifier
                                            .align(Alignment.Center),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                ) { page ->
                    when (page) {
                        0 -> OverviewPage(hotel = hotel)
                        1 -> RoomTypePage()
                        2 -> PolicyPage()
                        3 -> RatePage()
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun SearchScreenPreview() {
    val navController = rememberNavController()
    val hotel = Hotel(
        id = 1L,
        type = "Hotel",
        name = "Sample Hotel",
        link = "https://example.com",
        checkInTime = "14:00",
        checkOutTime = "12:00",
        hotelClass = "5-star",
        extractedHotelClass = 5,
        overallRating = 4.5,
        reviews = 200,
        locationRating = 4.0,
        propertyToken = null,
        serpapiPropertyDetailsLink = null,
        gpsCoordinates = null,
        ratePerNight = null,
        amenities = null,
        nearbyPlaces = null,
        images = null,
        ratings = null,
        reviewsBreakdown = null
    )
    DetailScreen(navController = navController,hotel = hotel)
}
