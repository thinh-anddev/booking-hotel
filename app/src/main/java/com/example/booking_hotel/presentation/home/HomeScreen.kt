package com.example.booking_hotel.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.booking_hotel.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}