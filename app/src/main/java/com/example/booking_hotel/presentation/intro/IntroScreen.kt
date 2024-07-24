package com.example.booking_hotel.presentation.intro

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.booking_hotel.R
import com.example.booking_hotel.presentation.intro.components.IntroPage
import com.example.booking_hotel.presentation.intro.components.PageIndicator
import com.example.booking_hotel.presentation.navgraph.Route
import com.example.booking_hotel.ui.theme.Background
import kotlinx.coroutines.launch

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background)
    ) {
        val pageState = rememberPagerState(
            initialPage = 0
        ) {
            pages.size
        }
        val buttonState = remember {
            derivedStateOf {
                when (pageState.currentPage) {
                    0 -> "Tiếp"
                    1 -> "Tiếp"
                    2 -> "Bắt đầu"
                    else -> "Tiếp"
                }
            }
        }

        HorizontalPager(state = pageState) { index ->
            IntroPage(page = pages[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                pageSize = pages.size,
                selectedPage = pageState.currentPage,
                selectedBackground = R.drawable.selected_dot,
                unSelectedBackground = R.drawable.unselected_dot
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val scope = rememberCoroutineScope()
                Text(
                    text = buttonState.value, style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.lato_regular))
                    ),
                    modifier = Modifier.clickable {
                        scope.launch {
                            if (pageState.currentPage == 2) {
                                navController.navigate(Route.RegisterScreen.route)
                            } else {
                                pageState.animateScrollToPage(page = pageState.currentPage + 1)
                            }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}