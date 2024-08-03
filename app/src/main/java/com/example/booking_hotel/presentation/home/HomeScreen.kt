package com.example.booking_hotel.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.booking_hotel.R
import com.example.booking_hotel.presentation.home.components.CheckWidget
import com.example.booking_hotel.presentation.home.components.NumberPeopleWidget
import com.example.booking_hotel.presentation.home.components.SearchBar
import com.example.booking_hotel.presentation.home.components.SearchButton
import com.example.booking_hotel.presentation.navgraph.Route

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val searchQuery by viewModel.searchQuery
    val children by viewModel.children
    val adult by viewModel.adult

    Box(
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
                .padding(horizontal = 16.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF4B5842))
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp)
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

                Image(
                    painterResource(id = R.drawable.avatar_test),
                    contentDescription = null,
                    modifier = Modifier.constrainAs(avatar) {
                        end.linkTo(parent.end)
                        top.linkTo(title.top)
                        bottom.linkTo(content.bottom)
                    }
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            SearchBar(
                text = searchQuery,
                placeHolder = "Da lat, Lam Dong",
                onValueChange = { viewModel.onSearchChange(it) },
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CheckWidget(check = "Check-in", date = "asdfasd", modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(20.dp))
                CheckWidget(check = "Check-out", date = "asdfasd", modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(16.dp))
            NumberPeopleWidget(
                adults = adult.toString(),
                childrens = children.toString(),
                plusAdult = { viewModel.countAdult(true) },
                plusChildren = { viewModel.countChildren(true) },
                minusAdult = { viewModel.countAdult(false) },
                minusChildren = { viewModel.countChildren(false) }
            )
            Spacer(modifier = Modifier.height(22.dp))
            SearchButton(onClick = {
                navController.navigate(
                    route = Route.SearchScreen.passData(
                        searchQuery = searchQuery,
                        checkInDate = "2024-08-03",
                        checkOutDate = "2024-08-04",
                        children = children.toString(),
                        adult = adult.toString()
                    )
                )
            })
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen()
//}