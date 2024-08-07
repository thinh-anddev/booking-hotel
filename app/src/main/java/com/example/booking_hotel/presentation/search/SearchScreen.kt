package com.example.booking_hotel.presentation.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.room.util.query
import com.example.booking_hotel.R
import com.example.booking_hotel.helper.showToast
import com.example.booking_hotel.presentation.search.components.BottomButton
import com.example.booking_hotel.presentation.search.components.listBottomButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavController,
    searchQuery: String,
    checkInDate: String,
    checkOutDate: String,
    adult: String,
    children: String,
    viewModel: SearchViewModel = hiltViewModel(),
    event: (SearchEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(Unit) {
        viewModel.searchByQuery(
            query = searchQuery,
            checkInDate = checkInDate,
            checkOutDate = checkOutDate,
            adult = adult,
            children = children
        )
    }

    val properties = viewModel.properties.collectAsLazyPagingItems()
    val context = LocalContext.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomButton(
                items = listBottomButton,
                onItemClick = {
                    index ->
                    when (index) {
                        0 -> {
                            event(SearchEvent.Price)
                            "Sorted".showToast(context)
                        }
                        1 -> event(SearchEvent.View)
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFF4B5842))
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 20.dp)
                ) {
                    val (btnBack, title) = createRefs()
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.constrainAs(btnBack) {
                            start.linkTo(parent.start)
                        }) {
                        Image(painterResource(id = R.drawable.ic_back), contentDescription = null)
                    }
                    Column(
                        modifier = Modifier.constrainAs(title) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = searchQuery,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 24.sp,
                                fontFamily = FontFamily(Font(R.font.lato_bold))
                            ),
                        )
                        Text(
                            text = "$adult người lớn $children trẻ em",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.lato_regular))
                            )
                        )
                    }
                }
                HotelList(properties = properties)
            }
        }
    }
}

//@Preview
//@Composable
//fun SearchScreenPreview() {
//    val navController = rememberNavController()
//    SearchScreen(navController = navController)
//}