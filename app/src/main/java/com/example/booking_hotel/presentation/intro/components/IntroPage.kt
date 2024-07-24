package com.example.booking_hotel.presentation.intro.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booking_hotel.R
import com.example.booking_hotel.presentation.intro.Page
import com.example.booking_hotel.presentation.intro.pages
import com.example.booking_hotel.ui.theme.Background
import com.example.booking_hotel.ui.theme.TitleColor

@Composable
fun IntroPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = page.image), contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.7f),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = page.title, style = TextStyle(
                color = TitleColor,
                fontFamily = FontFamily(Font(R.font.lato_bold)),
                fontSize = 28.sp
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = page.content, style = TextStyle(
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontSize = 18.sp
            ), textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = 60.dp)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun IntroPagePreview() {
//    IntroPage(page = pages[0])
//}